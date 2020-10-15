package com.eli.coupons_2end.repository;


import com.eli.coupons_2end.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);

    boolean existsByPassword(String password);


}
