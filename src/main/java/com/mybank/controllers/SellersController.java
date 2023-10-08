package com.mybank.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mybank.dto.*;
import com.mybank.entities.*;

import com.mybank.repositories.*;



import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/sellers")
public class SellersController {

  @Autowired
  private SaleRepository saleRepository;

  @Autowired 
  private SellerRepository sellerRepository;

  @Autowired
  private FinancialProductKindRepository productKindRepository;

  @GetMapping("/sales-summary/{sellerId}/{year}/{month}")
  public ResponseEntity<SellerMonthlyPointsDTO> getSalesSummary(
		  @PathVariable Long sellerId,
	        @PathVariable int year,
	        @PathVariable int month) {

    // Obtener vendedor por ID
    Seller seller = sellerRepository.findById(sellerId)
            .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

    // Obtener ventas del vendedor para el año y mes especificados
    List<Sale> sales = saleRepository.findAllBySeller(seller);
    List<Sale> filteredSales = sales.stream()
            .filter(sale -> sale.getDate().getYear() == year && sale.getDate().getMonthValue() == month)
            .collect(Collectors.toList());

    // Verificar si hay ventas en el mes y año especificados
    if (filteredSales.isEmpty()) {
        return ResponseEntity.badRequest().body(new SellerMonthlyPointsDTO());
    }

    // Calcular resumen de ventas por producto y puntos totales
    SellerMonthlyPointsDTO summary = calculateSalesSummary(filteredSales);

    return ResponseEntity.ok(summary);
  }

  private SellerMonthlyPointsDTO calculateSalesSummary(List<Sale> sales) {
	  SellerMonthlyPointsDTO summary = new SellerMonthlyPointsDTO();
    List<ProductSummary> productSummaries = new ArrayList<>();
    Map<String, Double> pointsByProduct = new HashMap<>();
    double totalPoints = 0.0;

    for (Sale sale : sales) {
      for (SaleDetail detail : sale.getDetails()) {
        FinancialProduct product = detail.getProduct();
        FinancialProductKind kind = productKindRepository.findById(product.getId())
                .orElseThrow(() -> new RuntimeException("Tipo de producto no encontrado"));

        double points = calculatePointsForSaleDetail(detail, kind);
        double amount = detail.getAmount();

        totalPoints += points;

        String productName = product.getName();
        pointsByProduct.put(productName, pointsByProduct.getOrDefault(productName, 0.0) + points);

        // Si el producto aún no se ha agregado al resumen, agrégalo
        boolean productExistsInSummary = productSummaries.stream()
            .anyMatch(summaryItem -> summaryItem.getProductName().equals(productName));

        if (!productExistsInSummary) {
            ProductSummary productSummary = new ProductSummary();
            productSummary.setProductName(productName);
            productSummary.setPointsPerAmount(kind.getPointsPerAmount());
            productSummaries.add(productSummary);
        }
      }
    }

    // Actualizar los totales de cantidad vendida y puntos por producto
    for (ProductSummary productSummary : productSummaries) {
        String productName = productSummary.getProductName();
        double totalProductPoints = pointsByProduct.get(productName);
        productSummary.setTotalPointsByProduct(totalProductPoints);

        // Calcular la cantidad total vendida
        double totalAmountSold = sales.stream()
                .flatMap(sale -> sale.getDetails().stream())
                .filter(detail -> detail.getProduct().getName().equals(productName))
                .mapToDouble(SaleDetail::getAmount)
                .sum();

        productSummary.setTotalAmountSold(totalAmountSold);
    }

    summary.setProductSummaries(productSummaries);
    summary.setTotalPoints(totalPoints);

    return summary;
  }

  private double calculatePointsForSaleDetail(SaleDetail detail, FinancialProductKind kind) {
    return (double)(detail.getAmount() * kind.getPointsPerAmount());
  }
  
  @GetMapping("/sales-summary/{year}/{month}")
  public ResponseEntity<List<SalesSummaryForYearAndMonthDTO>> getSalesSummaryForYearAndMonth(
          @PathVariable int year,
          @PathVariable int month) {

      List<Seller> sellers = sellerRepository.findAll();

      if (sellers.isEmpty()) {
          return ResponseEntity.notFound().build();
      }

      List<SalesSummaryForYearAndMonthDTO> summaries = new ArrayList<>();

      for (Seller seller : sellers) {
          List<Sale> sales = saleRepository.findAllBySeller(seller);
          List<Sale> filteredSales = sales.stream()
                  .filter(sale -> sale.getDate().getYear() == year && sale.getDate().getMonthValue() == month)
                  .collect(Collectors.toList());

          SalesSummaryForYearAndMonthDTO summary = calculateSalesSummary(filteredSales, seller);
          summaries.add(summary);
      }

      // Eliminar duplicados de productos y calcular sumatorias
      summaries.forEach(summary -> {
          Map<String, ProductSummary> productSummaryMap = new HashMap<>();
          List<ProductSummary> uniqueProductSummaries = new ArrayList<>();
          double totalPoints = 0.0;

          for (ProductSummary productSummary : summary.getProductSummaries()) {
              String productName = productSummary.getProductName();

              if (productSummaryMap.containsKey(productName)) {
                  // Producto ya existe, actualizar sumatorias
                  ProductSummary existingSummary = productSummaryMap.get(productName);
                  existingSummary.setTotalAmountSold(existingSummary.getTotalAmountSold() + productSummary.getTotalAmountSold());
                  existingSummary.setTotalPointsByProduct(existingSummary.getTotalPointsByProduct() + productSummary.getTotalPointsByProduct());
              } else {
                  // Producto no existe, agregarlo al mapa
                  productSummaryMap.put(productName, productSummary);
                  uniqueProductSummaries.add(productSummary);
              }

              totalPoints += productSummary.getTotalPointsByProduct();
          }

          summary.setProductSummaries(uniqueProductSummaries);
          summary.setTotalPoints(totalPoints);
      });

      return ResponseEntity.ok(summaries);
  }

  private SalesSummaryForYearAndMonthDTO calculateSalesSummary(List<Sale> sales, Seller seller) {
      SalesSummaryForYearAndMonthDTO summary = new SalesSummaryForYearAndMonthDTO();
      List<ProductSummary> productSummaries = new ArrayList<>();

      for (Sale sale : sales) {
          for (SaleDetail detail : sale.getDetails()) {
              FinancialProduct product = detail.getProduct();
              FinancialProductKind kind = productKindRepository.findById(product.getId())
                      .orElseThrow(() -> new RuntimeException("Tipo de producto no encontrado"));

              double points = calculatePointsForSaleDetail(detail, kind);
              double amount = detail.getAmount();

              ProductSummary productSummary = new ProductSummary();
              productSummary.setProductName(product.getName());
              productSummary.setPointsPerAmount(kind.getPointsPerAmount());
              productSummary.setTotalAmountSold(amount);
              productSummary.setTotalPointsByProduct(points);

              productSummaries.add(productSummary);
          }
      }

      summary.setSellerName(seller.getPerson().toString());
      summary.setProductSummaries(productSummaries);

      return summary;
  }
}




class SalesSummaryRequest {

	  private Long sellerId;
	  private int year;
	  private int month;
	  
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}

}
