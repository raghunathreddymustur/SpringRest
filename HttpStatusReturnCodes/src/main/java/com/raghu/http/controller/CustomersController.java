package com.raghu.http.controller;

import com.raghu.http.dao.CustomersDao;
import com.raghu.http.ds.Customer;
import com.raghu.http.ds.CustomerCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.*;

@RestController
public class CustomersController {

    @Autowired
    private CustomersDao customersDao;

    @GetMapping("/customers")
    public Resources<Resource<Customer>> listCustomers() {
        List<Resource<Customer>> customers = StreamSupport.stream(customersDao.findAll().spliterator(), false)
                .map(customer -> new Resource<>(customer, linkTo(methodOn(CustomersController.class).getCustomer(customer.getId())).withSelfRel()))
                .collect(Collectors.toList());

        return new Resources<>(
                customers,
                linkTo(methodOn(CustomersController.class).listCustomers()).withSelfRel(),
                linkTo(methodOn(IndexController.class).index()).withRel("index")
        );
    }

    @GetMapping("/customers/{id}")
    public Resource<Customer> getCustomer(@PathVariable int id) {
        Customer customer = customersDao.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));

        return new Resource<>(
                customer,
                linkTo(methodOn(CustomersController.class).getCustomer(customer.getId())).withSelfRel(),
                linkTo(methodOn(CustomersController.class).listCustomers()).withRel("customers")
        );
    }

    @PostMapping("/customers")
    public ResponseEntity<Resource<Customer>> createCustomer(@RequestBody @Valid Customer customer) {
        Customer createdCustomer = customersDao.save(customer);
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(CustomersController.class).getCustomer(customer.getId()));

        return created(linkBuilder.toUri()).body(new Resource<>(
                createdCustomer,
                linkBuilder.withSelfRel()
        ));
    }

    @PostMapping("/customers/search")
    public ResponseEntity<List<Customer>> searchCustomerByCriteria(@RequestBody CustomerCriteria customerCriteria) {
        return ok()
                .body(customersDao.findByFirstNameLike(customerCriteria.getFirstNameLike()));
    }

    // for this example, also consider using HTTP HEAD instead of HTTP POST
    @PostMapping("/customers/count")
    public ResponseEntity<List<Customer>> countCustomers() {
        return noContent()
                .header("Customers-Count", String.valueOf(customersDao.count()))
                .build();

    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Resource<Customer>> putCustomer(@PathVariable int id, @RequestBody @Valid Customer customer) {
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(CustomersController.class).getCustomer(id));
        BodyBuilder bodyBuilder;

        if (customersDao.existsById(id)) {
            bodyBuilder = ResponseEntity.ok();
        } else {
            bodyBuilder = ResponseEntity.created(linkBuilder.toUri());
        }

        customer.setId(id);
        Customer savedCustomer = customersDao.save(customer);

        return bodyBuilder.body(new Resource<>(
                savedCustomer,
                linkBuilder.withSelfRel(),
                linkTo(methodOn(CustomersController.class).listCustomers()).withRel("customers")
        ));
    }

    @PutMapping("/customers")
    public ResponseEntity putCustomers(@RequestBody @Valid Collection<Customer> customers) {
        customersDao.deleteAll();
        Iterable<Customer> updatedCustomers = customersDao.saveAll(customers);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity deleteCustomer(@PathVariable int id) {
        if (customersDao.existsById(id)) {
            customersDao.deleteById(id);
            return new ResponseEntity(NO_CONTENT);
        } else
            return new ResponseEntity(NOT_FOUND);
    }
}
