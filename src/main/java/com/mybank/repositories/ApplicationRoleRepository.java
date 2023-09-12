package com.mybank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybank.entities.ApplicationRole;

public interface ApplicationRoleRepository extends JpaRepository<ApplicationRole, Long> {
	public ApplicationRole findByName(String name);
}
