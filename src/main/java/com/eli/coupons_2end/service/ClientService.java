package com.eli.coupons_2end.service;

import com.eli.coupons_2end.exceptions.LoginException;
import com.eli.coupons_2end.repository.CompanyRepository;
import com.eli.coupons_2end.repository.CouponRepository;
import com.eli.coupons_2end.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public abstract class ClientService {

    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;


    public abstract boolean login(String email, String password) throws LoginException;
}
