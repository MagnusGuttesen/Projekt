package model;

import java.sql.Date;

public class RegisteredProduct {
    private String productName;
    private int productid;
    private int registeredid;
    private Date registrationdate;
    private Date productExp;
    private int quantity;
    private int cageNo;

    public RegisteredProduct() {
    }

    public RegisteredProduct(String productName, int productid, int quantity, Date productExp, int cageNo) {
        this.productName = productName;
        this.productid = productid;
        this.quantity = quantity;
        this.productExp = productExp;
        this.cageNo = cageNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getProductExp() {
        return productExp;
    }

    public void setProductExp(Date productExp) {
        this.productExp = productExp;
    }

    public int getCageNo() {
        return cageNo;
    }

    public void setCageNo(int cageNo) {
        this.cageNo = cageNo;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getRegisteredid() {
        return registeredid;
    }

    public void setRegisteredid(int registeredid) {
        this.registeredid = registeredid;
    }

    public Date getRegistrationdate() {
        return registrationdate;
    }

    public void setRegistrationdate(Date registrationdate) {
        this.registrationdate = registrationdate;
    }

    @Override
    public String toString() {
        return productName + " (antal: " + quantity + ", udløb: " + productExp + ", bur: " + cageNo + ")";
    }
}


	

