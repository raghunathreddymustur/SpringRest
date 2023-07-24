package com.raghu.rest.intro.dao;

import com.raghu.rest.intro.pojo.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomersDao extends CrudRepository<Customer, Integer> {
}
