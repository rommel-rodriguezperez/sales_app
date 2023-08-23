package com.mybank.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import com.mybank.entities.Role;

@Entity
@Table(name = "Employees")
@PrimaryKeyJoinColumn(name = "id")
public class Employee extends Person {
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    // TODO: Any idea how to retrieve the managed employees and the sales from
    // the role attribute given that it can be of subclass Manager or Seller?
    // NOTE: Seller is not implemented yet, it has to replace SalesPerson,
    // or I can transform SalesPerson into seller.

    // Employee-specific attributes
}
