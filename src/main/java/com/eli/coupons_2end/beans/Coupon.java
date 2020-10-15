package com.eli.coupons_2end.beans;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;


@NoArgsConstructor
@Entity
@Data
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private int id;

    private int companyId;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String title;

    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private int amount;

    private double price;

    private String image;


    public Coupon(int companyId,
                  Category category,
                  String title,
                  String description,
                  LocalDate startDate,
                  LocalDate endDate,
                  int amount,
                  double price,
                  String image) {
        this.companyId = companyId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }
}
