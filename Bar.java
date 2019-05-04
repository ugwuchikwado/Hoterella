package com.bean;
// Generated Mar 19, 2019 12:41:02 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Bar generated by hbm2java
 */
public class Bar  implements java.io.Serializable {


     private String drinkid;
     private String brand;
     private String name;
     private int quantity;
     private double unitprice;
     private Date purchasedate;
     private String checkedby;

    public Bar() {
    }

    public Bar(String drinkid, String brand, String name, int quantity, double unitprice, Date purchasedate, String checkedby) {
       this.drinkid = drinkid;
       this.brand = brand;
       this.name = name;
       this.quantity = quantity;
       this.unitprice = unitprice;
       this.purchasedate = purchasedate;
       this.checkedby = checkedby;
    }
   
    public String getDrinkid() {
        return this.drinkid;
    }
    
    public void setDrinkid(String drinkid) {
        this.drinkid = drinkid;
    }
    public String getBrand() {
        return this.brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getUnitprice() {
        return this.unitprice;
    }
    
    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }
    public Date getPurchasedate() {
        return this.purchasedate;
    }
    
    public void setPurchasedate(Date purchasedate) {
        this.purchasedate = purchasedate;
    }
    public String getCheckedby() {
        return this.checkedby;
    }
    
    public void setCheckedby(String checkedby) {
        this.checkedby = checkedby;
    }




}

