package com.example.zilvinastomkevicius.nostarve4u.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by Zilvinas Tomkevicius on 2018-05-21.
 */

public class User {

    @JsonProperty("UserID")
    public int UserID;
    @JsonProperty("Name")
    public String Name;
    @JsonProperty("Password")
    public String Password;
    @JsonProperty("Email")
    public String Email;
    @JsonProperty("LastLogin")
    public Date Date;

}
