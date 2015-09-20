package com.illusionbox.offerme.model;
// Generated 21-Sep-2015 02:18:11 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * RestaurantManager generated by hbm2java
 */
public class RestaurantManager  implements java.io.Serializable {


     private String email;
     private Administrator administrator;
     private String password;
     private String name;
     private String address;
     private String tel;
     private String state;
     private String remarks;
     private Set<Restaurant> restaurants = new HashSet<Restaurant>(0);

    public RestaurantManager() {
    }

	
    public RestaurantManager(String email, Administrator administrator, String password, String name) {
        this.email = email;
        this.administrator = administrator;
        this.password = password;
        this.name = name;
    }
    public RestaurantManager(String email, Administrator administrator, String password, String name, String address, String tel, String state, String remarks, Set<Restaurant> restaurants) {
       this.email = email;
       this.administrator = administrator;
       this.password = password;
       this.name = name;
       this.address = address;
       this.tel = tel;
       this.state = state;
       this.remarks = remarks;
       this.restaurants = restaurants;
    }
   
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Administrator getAdministrator() {
        return this.administrator;
    }
    
    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public Set<Restaurant> getRestaurants() {
        return this.restaurants;
    }
    
    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }




}


