package com.raghu.responsebody.controller;

import com.raghu.responsebody.annotation.RestEndpoint;
import com.raghu.responsebody.dao.AddressesDao;
import com.raghu.responsebody.dao.CustomersDao;
import com.raghu.responsebody.ds.Address;
import com.raghu.responsebody.ds.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestEndpoint
@RequestMapping("/api/v4")
public class ApiControllerV4 {

    @Autowired
    private CustomersDao customersDao;
    @Autowired
    private AddressesDao addressesDao;

    // curl localhost:8080/api/v4/customers |jq
    @GetMapping("customers")
    public Iterable<Customer> listCustomers() {
        return customersDao.findAll();
    }

    // curl localhost:8080/api/v4/addresses |jq
    @GetMapping("addresses")
    public Iterable<Address> listAddresses() {
        return addressesDao.findAll();
    }
}
