package com.teamhalum.shahad.bsmrudiary;

public class Contacts {

    private String name, post, email, office_num, res_num, mobile_num1, mobile_num2;

    public Contacts() {

    }

    public Contacts(String name, String post, String email, String office_num, String res_num, String mombile_num1, String mobile_num2) {
        this.name = name;
        this.post = post;
        this.email = email;
        this.office_num = office_num;
        this.res_num = res_num;
        this.mobile_num1 = mombile_num1;
        this.mobile_num2 = mobile_num2;
    }

    public String getName() {
        return name;
    }

    public String getPost() {
        return post;
    }

    public String getEmail() {
        return email;
    }

    public String getOffice_num() {
        return office_num;
    }

    public String getRes_num() {
        return res_num;
    }

    public String getMobile_num1() {
        return mobile_num1;
    }

    public String getMobile_num2() {
        return mobile_num2;
    }
}
