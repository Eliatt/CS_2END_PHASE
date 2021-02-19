package com.eli.coupons_3rd.repository;


import com.eli.coupons_3rd.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    Coupon getByCompanyIdAndTitle(int companyId, String title);


}
