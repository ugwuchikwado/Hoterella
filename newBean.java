package com.bean;

import java.sql.Date;

/**
 *
 * @author AUSTINS
 */
public class newBean {

    private String email;
    private String responsibility;
    private String messageTitle;
    private String messageBody;
    private int messageid;
    private String beverageId;
    private int quantitysold;
    private double pricesold;
    private Date date;

    private String name;
    private String category;
    private String quantity;
    private int unitprice;
    private int totalprice;
    private String purchasedate;
    private int number_of_plates;
    private String datesold;
    private String checkeby;
    private String paymentMethod;

    public newBean() {
    }

    public newBean(String responsibility, String email) {
        this.responsibility = responsibility;
        this.email = email;
    }

    public newBean(String email, String messageTitle, String messageBody) {
        this.email = email;
        this.messageTitle = messageTitle;
        this.messageBody = messageBody;
    }

    public newBean(int messageid, String email, String messageTitle, String messageBody) {
        this.messageid = messageid;
        this.email = email;
        this.messageTitle = messageTitle;
        this.messageBody = messageBody;
    }

    public newBean(String name, String category, String quantity, int unitprice, int totalprice, String purchasedate, String checkedby) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unitprice = unitprice;
        this.totalprice = totalprice;
        this.purchasedate = purchasedate;
        this.checkeby = checkedby;
    }

    public newBean(String name, int number_of_plates, int unitprice, int totalprice, String datesold, String checkedby) {
        this.name = name;
        this.unitprice = unitprice;
        this.totalprice = totalprice;
        this.number_of_plates = number_of_plates;
        this.datesold = datesold;
        this.checkeby = checkedby;
    }

    public newBean(String beverageId, String category, String name, int quantitysold, double pricesold,  Date date, String checkedby, String paymentMethod) {
        this.beverageId = beverageId;
        this.quantitysold = quantitysold;
        this.pricesold = pricesold;
        this.name = name;
        this.category = category;
        this.date = date;
        this.checkeby = checkedby;
        this.paymentMethod = paymentMethod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public int getMessageid() {
        return messageid;
    }

    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(int unitprice) {
        this.unitprice = unitprice;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public String getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(String purchasedate) {
        this.purchasedate = purchasedate;
    }

    public int getNumber_of_plates() {
        return number_of_plates;
    }

    public void setNumber_of_plates(int number_of_plates) {
        this.number_of_plates = number_of_plates;
    }

    public String getDatesold() {
        return datesold;
    }

    public void setDatesold(String datesold) {
        this.datesold = datesold;
    }

    public String getBeverageId() {
        return beverageId;
    }

    public void setBeverageId(String beverageId) {
        this.beverageId = beverageId;
    }

    public int getQuantitysold() {
        return quantitysold;
    }

    public void setQuantitysold(int quantitysold) {
        this.quantitysold = quantitysold;
    }

    public double getPricesold() {
        return pricesold;
    }

    public void setPricesold(double pricesold) {
        this.pricesold = pricesold;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCheckedby() {
        return checkeby;
    }

    public void setCheckedby(String checkedby) {
        this.checkeby = checkedby;
    }

    public String getCheckeby() {
        return checkeby;
    }

    public void setCheckeby(String checkeby) {
        this.checkeby = checkeby;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
