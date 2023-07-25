package com.raghu.responsebody.dao;

import com.raghu.responsebody.ds.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomersDao extends CrudRepository<Customer, Integer> {
}
