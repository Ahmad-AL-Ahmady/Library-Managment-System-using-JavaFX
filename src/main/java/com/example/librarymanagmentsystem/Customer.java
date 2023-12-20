package com.example.librarymanagmentsystem;

public class Customer {
    private int customerId;
    private String customerName,email,customerNumber;

    public Customer(int customerId, String customerName, String email, String customerNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.customerNumber = customerNumber;
    }

    public Customer() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", customerName=" + customerName + ", email=" + email + ", customerNumber=" + customerNumber + '}';
    }

}
