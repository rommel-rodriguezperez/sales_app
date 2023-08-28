package com.mybank.mappers;

import org.springframework.stereotype.Service;

import com.mybank.dto.ManagerDto;
import com.mybank.entities.Manager;
import com.mybank.entities.Person;

@Service
public class ManagerMapper {

	public ManagerDto toDto(Manager manager) {
		Person person = manager.getPerson();
		ManagerDto dto = new ManagerDto();

		dto.setId(manager.getId());
		dto.setFirstName(person.getFirstName());
		dto.setLastName(person.getLastName());
		dto.setDocumentType(person.getDocumentType().name());
		dto.setDocumentNumber(person.getDocumentNumber());
		dto.setCellPhoneNumber(person.getCellPhoneNumber());
		dto.setRole(manager.getRole().getName());

		return dto;
	}
}
