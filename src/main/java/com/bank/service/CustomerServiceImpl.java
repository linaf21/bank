package com.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.domain.Customer;
import com.bank.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository CustomerRepository;
	
	@Autowired
	Validator validator;


	@Override
	public void validate(Customer entity) throws ConstraintViolationException {
		Set<ConstraintViolation<Customer>> ConstraintViolations =  validator.validate(entity);
		
		if(!ConstraintViolations.isEmpty()) {
			throw new ConstraintViolationException(ConstraintViolations);
		}
	}
	
	@Override
	public List<Customer> findAll() {
		return CustomerRepository.findAll();
	}

	@Override
	public Optional<Customer> findById(Integer id) {
		return CustomerRepository.findById(id);
	}

	@Override
	public Long count() {
		return CustomerRepository.count();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Customer save(Customer entity) throws Exception {
		
		if(entity == null) {
			throw new Exception("El customer es nulo");
		}
		
		validate(entity);
		
		if(CustomerRepository.existsById(entity.getCustId()) == true) {
			throw new Exception("El customer con id:"+entity.getCustId()+" ya existe");
		}
		
		return CustomerRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Customer update(Customer entity) throws Exception {
		if(entity == null) {
			throw new Exception("El customer es nulo");
		}
		
		validate(entity);
		
		if(CustomerRepository.existsById(entity.getCustId()) == false) {
			throw new Exception("El customer con id:"+entity.getCustId()+" no existe");
		}
		
		return CustomerRepository.save(entity);
	}

	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Customer entity) throws Exception {
		if(entity == null) {
			throw new Exception("El customer es nulo");
		}
		
		if(entity.getCustId() == null || entity.getCustId() == 0) {
			throw new Exception("El customer id es nulo");			
		}
		
		if(CustomerRepository.existsById(entity.getCustId()) == false) {
			throw new Exception("El customer con id:"+entity.getCustId()+" no existe");
		}
		
		CustomerRepository.findById(entity.getCustId()).ifPresent(customer->{
			if(customer.getAccounts().isEmpty() == false) {
				throw new RuntimeException("El customer tiene Accounts asociadas");		
			}
			
			if(customer.getRegisteredAccounts().isEmpty() == false) {
				throw new RuntimeException("El customer tiene RegisteredAccounts asociadas");		
			}		
		});
		
		CustomerRepository.deleteById(entity.getCustId());		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		
		if(id == null || id <= 0) {
			throw new Exception("El id es nulo o menor o igual a cero");
		}
		
		if(CustomerRepository.existsById(id) == false) {
			throw new Exception("El customer con id:"+id+" no existe");
		}
		
		delete(CustomerRepository.findById(id).get());		
	}
}
