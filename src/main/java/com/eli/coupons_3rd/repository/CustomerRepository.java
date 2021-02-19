package com.eli.coupons_3rd.repository;


import com.eli.coupons_3rd.beans.Coupon;
import com.eli.coupons_3rd.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);

    boolean existsByPassword(String password);

    Customer findById(int customerId);

    List<Customer> findByCoupons(Coupon coupon);











}
