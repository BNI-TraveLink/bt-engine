package com.btengine.btlink.controller;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import com.btengine.btlink.model.Customer;
import com.btengine.btlink.model.Login;
import com.btengine.btlink.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
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

    @GetMapping("/getCustomerByUserId/{userid}")
    public ResponseEntity<Optional<Customer>> getCustomerByUserId(@PathVariable String userid){

        Optional<Customer> customer  = customerService.getCustomerByUserId(userid);


        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(customer);
    }
}

