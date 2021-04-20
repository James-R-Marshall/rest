package com.web.rest;

import java.util.ArrayList;
import java.util.List;

import com.back.Entry;
import com.back.Order;
import com.back.SQLData;
import com.back.SQLPo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class POController {
    
    @GetMapping("/orders")
    public List<Order> listOrders(@RequestParam(value = "id", defaultValue = "1") String email){
        SQLPo init = new SQLPo();
        init.initializeDatabase("jdbc:mysql://216.137.177.30:3306/testDB?allowPublicKeyRetrieval=true&useSSL=false team3 UpdateTrello!1");
        List<Order> list = init.GenerateShortPOs(email);
        return list;
    }
    // @GetMapping("/products/product")
    // public Entry listProducts(@RequestParam(value = "id", defaultValue = "1") String id){
    //     SQLData init = new SQLData();
    //     init.initializeDatabase("jdbc:mysql://216.137.177.30:3306/testDB?allowPublicKeyRetrieval=true&useSSL=false team3 UpdateTrello!1");
    //     var entry = init.readEntry(id);
    //     return entry;
    // }

}

