package com.raghu.message.converter.dao;

import com.raghu.message.converter.ds.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomersDao extends CrudRepository<Customer, Integer> {
}
