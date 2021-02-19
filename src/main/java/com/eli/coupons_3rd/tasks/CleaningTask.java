package com.eli.coupons_3rd.tasks;

import com.eli.coupons_3rd.beans.Coupon;
import com.eli.coupons_3rd.repository.CouponRepository;
import com.eli.coupons_3rd.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
/*
operating instructions notes:
1.  on config.SchedulingConfiguration class : switch matchIfMissing = true/false to activate/deactivate task .
2. on resources.application.properties : set job time intervals .
*/
public class CleaningTask {
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CustomerService customerService;


    @Scheduled(initialDelayString = "PT2S", fixedDelayString = "${cleaningTask.delay}")
    void cleaningTask() {
        for (Coupon coupon : couponRepository.findAll()) {
            if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
                couponRepository.delete(coupon);
                System.out.println("Cleaning Expired Coupons in action : "
                        + coupon.getTitle()
                        + "  has expired, therefore removed from database");
                System.out.println("now is " +  Date.valueOf(LocalDate.now()));
            }

        }
    }
}
