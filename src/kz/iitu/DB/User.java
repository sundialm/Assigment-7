package kz.iitu.DB;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class User {

    private Long id;
    private String  email;
    private String password;
    private String full_name;
    private Date birth_date;
    private String picture_url;


    public int getYearsOld(){
       return Period.between(LocalDate.of(birth_date.getYear(), birth_date.getMonth(), birth_date.getDay()), LocalDate.of(new Date().getYear(), new Date().getMonth(), new Date().getDay())).getYears();
    }

    public User() {
    }

    public User(Long id, String email, String password, String full_name, Date birth_date, String picture_url) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
        this.birth_date = birth_date;
        this.picture_url = picture_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }
}
