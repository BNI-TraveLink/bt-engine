package com.btengine.btlink.controller;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import com.btengine.btlink.model.Customer;
import com.btengine.btlink.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
//@Api(value = "User Management System", description = "Operations pertaining to user in User Management System")
public class CustomerController {
    @Autowired
   CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


//    @ApiOperation(value = "get all customer")
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
