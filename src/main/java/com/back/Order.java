package com.back;



public class Order {
    int ID = 0;
    String productID = "";
    int quantity = 0;
    String date = "";
    String email = "";


    public String getDate() {
        return date;
    }
    public String getEmail() {
        return email;
    }
    public int getID() {
        return ID;
    }
    public String getProductID() {
        return productID;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
