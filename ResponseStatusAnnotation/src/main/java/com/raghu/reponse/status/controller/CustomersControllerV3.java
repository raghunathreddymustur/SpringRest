package com.raghu.reponse.status.controller;

import com.raghu.reponse.status.ds.Customer;
import com.raghu.reponse.status.exception.CustomBadRequestException;
import com.raghu.reponse.status.exception.CustomBandwidthLimitExceededException;
import com.raghu.reponse.status.exception.CustomPaymentRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomersControllerV3 {

    // curl -i http://localhost:8080/api/v3/customerA
    @GetMapping("/api/v3/customerA")
    public Customer getCustomerA() {
        throw new CustomBadRequestException();
    }

    // curl -i http://localhost:8080/api/v3/customerB
    @GetMapping("/api/v3/customerB")
    public Customer getCustomerB() {
        throw new CustomPaymentRequiredException();
    }

    // curl -i http://localhost:8080/api/v3/customerC
    @GetMapping("/api/v3/customerC")
    public Customer getCustomerC() {
        throw new CustomBandwidthLimitExceededException();
    }
}
