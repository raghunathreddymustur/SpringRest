package com.raghu.http.dao;

import com.raghu.http.ds.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersDao extends CrudRepository<Customer, Integer> {
    List<Customer> findByFirstNameLike(String firstNamePattern);
}
