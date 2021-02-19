package com.eli.coupons_3rd.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;

    @Setter(value = AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "companyId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coupon> coupons;

    public Company(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean addCoupon(Coupon coupon) {
        if (coupons == null) {
            coupons = new ArrayList<Coupon>();
        }
        coupon.setCompanyId(this.id);
        return coupons.add(coupon);
    }

    public boolean removeCoupon(Coupon coupon) {
        if (coupons == null) {
            return false;
        }
        return coupons.removeIf((Coupon coupon1) -> coupon1.getId() == coupon.getId());
    }
}
