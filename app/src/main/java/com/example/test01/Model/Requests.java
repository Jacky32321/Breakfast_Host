package com.example.test01.Model;

import java.util.List;

public class Requests {

    private String phne;
    private String name;
    private String total;
    private String status;
    private String description;

    public Requests(String phne, String name, String total, String status,String description) {
        this.phne = phne;
        this.name = name;
        this.total = total;
        this.status = status;
        this.description = description;
    }
    public Requests(){
        this.description = "無特別需求";
    }

    public String getphone() {
        return phne;
    }

    public void setPhone(String phne) {
        this.phne = phne;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

