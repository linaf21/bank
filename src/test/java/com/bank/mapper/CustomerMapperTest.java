package com.bank.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.bank.domain.Customer;
import com.bank.dto.CustomerDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class CustomerMapperTest {

	@Autowired
	CustomerMapper customerMapper;
	
	@Test
	void debeMappearDeCustomeraCustomerDTO() {
		
		//Arrange
		Customer customer = new Customer();
		customer.setAccounts(null);
		customer.setAddress("Avenida Siempre Viva 123");
		customer.setCustId(2020);
		customer.setEmail("homerojsimpson@mapu.com");
		customer.setEnable(true);
		customer.setName("Homeo J Simpson");
		customer.setPhone("555555");
		customer.setRegisteredAccounts(null);
		customer.setToken(UUID.randomUUID().toString().toUpperCase());
		
		CustomerDTO customerDTO = null;
		
		//Act
		customerDTO = customerMapper.toCustomerDTO(customer);
		
		//Assert
		assertNotNull(customerDTO);

	}

	@Test
	void debeMappearDeCustomerDTOaCustomer() {
		
		//Arrange
		CustomerDTO customerDTO =  new CustomerDTO();
		customerDTO.setAddress("Avenida Siempre vida 123");
		customerDTO.setCustId(2020);
		customerDTO.setEmail("hsimpson@mapu.com");
		customerDTO.setEnable(true);
		customerDTO.setName("Homero J Simpson");
		customerDTO.setPhone("234567");
		customerDTO.setToken(UUID.randomUUID().toString().toUpperCase());
		customerDTO.setDotyId(1);
		
		Customer customer = null;
		
		//Act
		customer = customerMapper.toCustomer(customerDTO);
		
		//Assert
		assertNotNull(customer);
	}

}
