package com.bean;
// Generated Mar 19, 2019 12:41:02 PM by Hibernate Tools 4.3.1



/**
 * Room generated by hbm2java
 */
public class Room  implements java.io.Serializable {


     private Integer roomid;
     private String roomtype;
     private String roomposition;
     private String roomnumber;
     private double unitprice;
     private String createdby;

    public Room() {
    }

    public Room(String roomtype, String roomposition, String roomnumber, double unitprice, String createdby) {
       this.roomtype = roomtype;
       this.roomposition = roomposition;
       this.roomnumber = roomnumber;
       this.unitprice = unitprice;
       this.createdby = createdby;
    }
   
    public Integer getRoomid() {
        return this.roomid;
    }
    
    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }
    public String getRoomtype() {
        return this.roomtype;
    }
    
    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }
    public String getRoomposition() {
        return this.roomposition;
    }
    
    public void setRoomposition(String roomposition) {
        this.roomposition = roomposition;
    }
    public String getRoomnumber() {
        return this.roomnumber;
    }
    
    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }
    public double getUnitprice() {
        return this.unitprice;
    }
    
    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }
    public String getCreatedby() {
        return this.createdby;
    }
    
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }




}

