package com.mybank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.dto.SaleRequestDto;
import com.mybank.entities.Customer;
import com.mybank.entities.FinancialProduct;
import com.mybank.entities.Sale;
import com.mybank.entities.SalesPerson;
import com.mybank.repositories.SaleRepository;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final SalesPersonService salesPersonService;
    private final CustomerService customerService;
    private final FinancialProductService financialProductService;

    @Autowired
    public SaleService(SaleRepository saleRepository, SalesPersonService salesPersonService,
                       CustomerService customerService, FinancialProductService financialProductService) {
        this.saleRepository = saleRepository;
        this.salesPersonService = salesPersonService;
        this.customerService = customerService;
        this.financialProductService = financialProductService;
    }

    public Sale createSale(SaleRequestDto saleRequestDto) {
        SalesPerson salesPerson = salesPersonService.getSalesPersonById(saleRequestDto.getSalesPersonId());
        Customer customer = customerService.getCustomerById(saleRequestDto.getCustomerId());
        FinancialProduct financialProduct = financialProductService.getFinancialProductById(saleRequestDto.getFinancialProductId());

        Sale sale = new Sale();
        sale.setDate(saleRequestDto.getDate());
        sale.setSalesPerson(salesPerson);
        sale.setCustomer(customer);
        sale.setAmount(saleRequestDto.getAmount());
        sale.setProduct(financialProduct);

        return saleRepository.save(sale);
    }

    // Other service methods
}
