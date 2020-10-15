package com.eli.coupons_2end.repository;


import com.eli.coupons_2end.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    Coupon getByCompanyIdAndTitle(int companyId, String title);


}
