package com.web.rest;

import java.util.ArrayList;

import com.back.Entry;
import com.back.SQLData;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProductController {
    
    @GetMapping("/products")
    public ArrayList<Entry> listProducts(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "10") int size){
        SQLData init = new SQLData();
        init.initializeDatabase("jdbc:mysql://216.137.177.30:3306/testDB?allowPublicKeyRetrieval=true&useSSL=false team3 UpdateTrello!1");
        var list = init.getEntries(page, size);
        return list;
    }
    @GetMapping("/products/product")
    public Entry listProducts(@RequestParam(value = "id", defaultValue = "1") String id){
        SQLData init = new SQLData();
        init.initializeDatabase("jdbc:mysql://216.137.177.30:3306/testDB?allowPublicKeyRetrieval=true&useSSL=false team3 UpdateTrello!1");
        var entry = init.readEntry(id);
        return entry;
    }

}

