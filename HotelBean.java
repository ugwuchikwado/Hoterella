package com.bean;

import java.io.FileInputStream;
import java.sql.Date;

/**
 *
 * @author AUSTINS
 */
public class HotelBean {
    
    private String name;
    private String email;
    private String phonenumber;
    private String address;
    private String gender;
    private String nextOfKin;
    private String nextOfKinPhone;
    private String password;
    private Date registerDate;
    private String responsibility;
    private String firstName;
    private String lastName;
    private Date dob;
    private FileInputStream profilePicture;
    private String roomtype;
    private String roomposition;
    private String roomnumber;
    private Date date;
    private String department;
    private String brand;
    private int quantity;
    private double unitprice;
    private int id;
    private int userid;
    private int otp; 
    
    private String medicalcondition;
    private String specification;
    private Date checkindate;
    private  Date checkoutdate;
    private String paymentmethod;
    private double price;
    private String hash;
    private int active;
    private String time;
    

    public HotelBean() {
    }

    public HotelBean(String name, String email, String phonenumber, String address, String gender, String nextOfKin, String nextOfKinPhone, String password, Date registerDate,String hash) {
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.address = address;
        this.gender = gender;
        this.nextOfKin = nextOfKin;
        this.nextOfKinPhone = nextOfKinPhone;
        this.password = password;
        this.registerDate = registerDate;
        this.hash = hash;
    }

    public HotelBean(String firstName, String lastName, String email, String phonenumber, String address, Date dob, String nextOfKin, String nextOfKinPhone, String department, int id) {
        this.email = email;
        this.phonenumber = phonenumber;
        this.address = address;
        this.nextOfKin = nextOfKin;
        this.nextOfKinPhone = nextOfKinPhone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.department = department;
        this.id = id;
    }
    
    public HotelBean(String email, String password, int active) {
        this.email = email;
        this.password = password;
        this.active = active;
    }

    public HotelBean(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    public HotelBean(String name, String email, String password, String responsibility) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.responsibility = responsibility;
    }
 public HotelBean(String email, String password, String responsibility) {       
        this.email = email;
        this.password = password;
        this.responsibility = responsibility;
    }
    
    public HotelBean(String firstName, String lastName, String email, String phonenumber, String address, Date dob, String gender, String nextOfKin, String nextOfKinPhone,String department, Date registerDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phonenumber = phonenumber;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.nextOfKin = nextOfKin;
        this.nextOfKinPhone = nextOfKinPhone; 
        this.department = department;        
        this.registerDate = registerDate;
    }

    public HotelBean(String roomtype, String roomposition, String roomnumber, double unitprice) {
        this.roomtype = roomtype;
        this.roomposition = roomposition;
        this.roomnumber = roomnumber;
        this.unitprice = unitprice;
    }

    public HotelBean(String brand, String name, int quantity, double unitprice, Date date) {
        this.brand = brand;
        this.name = name;
        this.quantity = quantity;
        this.unitprice = unitprice;
         this.date = date;
    }

    public HotelBean(int id, String name, String email, String phonenumber, String address, String gender, String nextOfKin, String nextOfKinPhone, Date registerDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.address = address;
        this.gender = gender;
        this.nextOfKin = nextOfKin;
        this.nextOfKinPhone = nextOfKinPhone;
        this.registerDate = registerDate;
    }

    public HotelBean(int id, String email, String password, String responsibility) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.responsibility = responsibility;        
    }

    public HotelBean(int id, String firstName, String lastName, String email, String phonenumber, String address, Date dob, String gender, String nextOfKin, String nextOfKinPhone, String department, Date date) {
        this.id = id;
        this.email = email;
        this.phonenumber = phonenumber;
        this.address = address;
        this.gender = gender;
        this.nextOfKin = nextOfKin;
        this.nextOfKinPhone = nextOfKinPhone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.date = date;
        this.department = department;
        
    }

    public HotelBean(int userid, String name, String email,String medicalcondition, String specification, Date checkindate, Date checkoutdate, String roomposition, String roomtype, String roomnumber, String paymentmethod, int otp, double price) {
        this.name = name;
        this.email = email;
        this.roomtype = roomtype;
        this.roomposition = roomposition;
        this.roomnumber = roomnumber;
        this.userid = userid;
        this.otp = otp;
        this.medicalcondition = medicalcondition;
        this.specification = specification;
        this.checkindate = checkindate;
        this.checkoutdate = checkoutdate;
        this.paymentmethod = paymentmethod;
        this.price = price;
    }

    public HotelBean(String name, String email, String phonenumber, String roomtype, String roomposition, Date checkindate, Date checkoutdate, String paymentmethod, double price) {
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.roomtype = roomtype;
        this.roomposition = roomposition;
        this.checkindate = checkindate;
        this.checkoutdate = checkoutdate;
        this.paymentmethod = paymentmethod;
        this.price = price;
    }

    public HotelBean(Date checkindate, String time) {
        this.checkindate = checkindate;
        this.time = time;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(String nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public String getNextOfKinPhone() {
        return nextOfKinPhone;
    }

    public void setNextOfKinPhone(String nextOfKinPhone) {
        this.nextOfKinPhone = nextOfKinPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }   

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public FileInputStream getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(FileInputStream profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getRoomposition() {
        return roomposition;
    }

    public void setRoomposition(String roomposition) {
        this.roomposition = roomposition;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public String getMedicalcondition() {
        return medicalcondition;
    }

    public void setMedicalcondition(String medicalcondition) {
        this.medicalcondition = medicalcondition;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Date getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(Date checkindate) {
        this.checkindate = checkindate;
    }

    public Date getCheckoutdate() {
        return checkoutdate;
    }

    public void setCheckoutdate(Date checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}
