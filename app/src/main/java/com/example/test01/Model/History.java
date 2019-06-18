package com.example.test01.Model;

public class History {
    private String description;
    private String name;
    private String phone;
    private String status;
    private String total;

    public History(){

    }
    public History(String description, String name, String phone, String status, String total) {
        this.description = description;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
