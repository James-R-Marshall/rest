package com.web.rest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.back.Entry;
import com.back.Order;
import com.back.PO;
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
     @GetMapping("/buy")
     public void buyProduct(@RequestParam(value = "id", defaultValue = "1") String id, @RequestParam(value = "email", defaultValue = "null") String email, @RequestParam(value = "loc", defaultValue = "null") String loc){
     SQLPo init = new SQLPo();
     init.initializeDatabase("jdbc:mysql://216.137.177.30:3306/testDB?allowPublicKeyRetrieval=true&useSSL=false team3 UpdateTrello!1");
     PO p = new PO();
     p.setCustomerLocation(loc);
     p.setDate(LocalDate.now().toString());
     p.setEmail(email);
     p.setProductID(id);
     init.createEntry(id, p);
    }

}

