package com.bean;
// Generated Mar 19, 2019 12:41:02 PM by Hibernate Tools 4.3.1



/**
 * Food generated by hbm2java
 */
public class Food  implements java.io.Serializable {


     private String foodid;
     private String name;
     private String category;
     private String quantity;
     private int unitprice;
     private String purchasedate;
     private String checkedby;

    public Food() {
    }

    public Food(String foodid, String name, String category, String quantity, int unitprice, String purchasedate, String checkedby) {
       this.foodid = foodid;
       this.name = name;
       this.category = category;
       this.quantity = quantity;
       this.unitprice = unitprice;
       this.purchasedate = purchasedate;
       this.checkedby = checkedby;
    }
   
    public String getFoodid() {
        return this.foodid;
    }
    
    public void setFoodid(String foodid) {
        this.foodid = foodid;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    public String getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public int getUnitprice() {
        return this.unitprice;
    }
    
    public void setUnitprice(int unitprice) {
        this.unitprice = unitprice;
    }
    public String getPurchasedate() {
        return this.purchasedate;
    }
    
    public void setPurchasedate(String purchasedate) {
        this.purchasedate = purchasedate;
    }
    public String getCheckedby() {
        return this.checkedby;
    }
    
    public void setCheckedby(String checkedby) {
        this.checkedby = checkedby;
    }




}


