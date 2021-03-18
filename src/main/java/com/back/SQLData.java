package com.back;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class SQLData {

    String connectionString = "";
    String username = "";
    String password = "";
    Connection con;
    Statement st;
    ResultSet rs;


    public void initializeDatabase(String filename) {
        StringParsers s = new StringParsers();
        var a = s.parseConnectionString(filename);
        connectionString = a[0];
        username = a[1];
        password = a[2];
        try {
            con = (Connection) DriverManager.getConnection(connectionString, username, password);
            st =  (Statement) con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");
            if(rs.next()){
                System.out.println("Connected to..." + rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public void createEntry(String ID, Entry e) {
        String statement="INSERT INTO DataEntries(productID,supplierID,stockQuantity,WholesaleCost,salePrice) VALUES('" + e.getProductID() + "', '" + e.getSupplierID() + "' , '"+ e.getStockQuantity() + "' , '" + e.getWholesaleCost() + "' , '" + e.getSalePrice() + "');" ;
        try {
            st.execute(statement);
            
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public Entry readEntry(String ID) {
        String statement = "SELECT * FROM DataEntries HAVING productID ='"+ ID + "';";
        Entry e = new Entry();
        try {
            rs = st.executeQuery(statement);
            rs.next();
            e.setProductID(rs.getString("productID"));
            e.setSalePrice(rs.getDouble("salePrice"));
            e.setStockQuantity(rs.getInt("stockQuantity"));
            e.setSupplierID(rs.getString("supplierID"));
            e.setWholesaleCost(rs.getDouble("wholesaleCost"));

        } catch (SQLException er) {
            er.printStackTrace();
        }
        return e;
    }

    public ArrayList<Entry> getEntries(int page, int size){
        String s = "";
        int at = page*size;
        int next = at + size;
        String statement = "SELECT * FROM DataEntries LIMIT "+ (page-1)*size + "," +  next + " ;";
        ArrayList<Entry> al = new ArrayList<Entry>();       
        try {
            rs = st.executeQuery(statement);
            
            while(rs.next() && at < next){
            s += rs.getString(1);
            s += "_" +  rs.getString(2);
            s += "_" +  rs.getString(3);
            s += "_" +  rs.getString(4);
            s +=  "_" + rs.getString(5);
            al.add(parseEntry(s));
            s = "";
            at++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al;
    }

    private Entry parseEntry(String s){
        Entry e = new Entry();
        var ar = s.split("_");
        e.setProductID(ar[0]);
        e.setSupplierID(ar[1]);
        e.setStockQuantity(Integer.parseInt(ar[2]));
        e.setWholesaleCost(Double.parseDouble(ar[3]));
        e.setSalePrice(Double.parseDouble(ar[4]));
        return e;
    }

    public void updateEntry(String ID, Entry e) {
        deleteEntry(ID);
        createEntry(ID,e);
       

    }

    public void deleteEntry(String ID) {
        String statement = "DELETE FROM DataEntries WHERE productID ='"+ ID + "';";
        try {
            st.execute(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveEntry(Entry e) {
        createEntry(e.getProductID(), e);

    }

    public int retSize() {
        // TODO Auto-generated method stub
        return 0;
    }
}