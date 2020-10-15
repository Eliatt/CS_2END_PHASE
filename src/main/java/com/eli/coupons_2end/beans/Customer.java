package com.eli.coupons_2end.beans;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    protected String password;

    private String firstName;

    private String lastName;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Coupon> coupons = new ArrayList<>();

    public Customer(String email,
                    String password,
                    String firstName,
                    String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

