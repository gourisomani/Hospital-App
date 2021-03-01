package com.example.fbgoogle;

public class Manager {
    String name;
    String age;
    String address;
    String contact_number;
    String complaints;
    String medicines;


    public Manager(String name, String age, String address, String contact_number, String complaints,String medicines) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.contact_number = contact_number;
        this.complaints = complaints;
        this.medicines = medicines;
    }

    public Manager() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getComplaints(){return complaints;}

    public void setComplaints(String complaints){this.complaints = complaints;}

    public String getMedicines(){return medicines;}

    public void setMedicines(String medicines){this.medicines = medicines;}
}
