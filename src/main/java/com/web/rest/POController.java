package com.web.rest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    @GetMapping("/order")
    public PO getOrder(@RequestParam(value="id", defaultValue = "1")String id){
        PO order;
        SQLPo init = new SQLPo();
        init.initializeDatabase("jdbc:mysql://216.137.177.30:3306/testDB?allowPublicKeyRetrieval=true&useSSL=false team3 UpdateTrello!1");
        order = init.getPo(Integer.parseInt(id));

        return order;
    }
     @GetMapping("/buy")
     public void buyProduct(@RequestParam(value = "id", defaultValue = "1") String id, @RequestParam(value = "email", defaultValue = "null") String email, @RequestParam(value = "loc", defaultValue = "null") String loc){
     SQLPo init = new SQLPo();
     SQLData data = new SQLData();
     
     final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
     // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        final String username = "purchasenoreply2@gmail.com";//
        final String password = "PineapplesOnPizza";
          Session session = Session.getDefaultInstance(props, 
                              new Authenticator(){
                                 protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(username, password);
                                 }});
     
                                 try {
                                     MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("PurchaseSupport"));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));  
        message.setHeader("Hi, everyone", "1");  
        message.setText("Hi, This mail is to inform you that you made a purchase of 1 " + id);  
        Transport.send(message);
        
    } catch (AddressException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (MessagingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    
     init.initializeDatabase("jdbc:mysql://216.137.177.30:3306/testDB?allowPublicKeyRetrieval=true&useSSL=false team3 UpdateTrello!1");
     data.initializeDatabase("jdbc:mysql://216.137.177.30:3306/testDB?allowPublicKeyRetrieval=true&useSSL=false team3 UpdateTrello!1");
     PO p = new PO();
     var item = data.readEntry(id);
     item.setStockQuantity(item.getStockQuantity() - 1);
     data.updateEntry(id, item);
     p.setCustomerLocation(loc);
     p.setDate(LocalDate.now().toString());
     p.quantity(1);
     p.setEmail(email);
     p.setProductID(id);
     init.createEntry(id, p);
    }
}


