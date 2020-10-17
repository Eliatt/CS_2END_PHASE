package com.eli.coupons_2end.tasks;

import com.eli.coupons_2end.beans.Coupon;
import com.eli.coupons_2end.exceptions.DoesNotExistException;
import com.eli.coupons_2end.repository.CouponRepository;
import com.eli.coupons_2end.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
//switch matchIfMissing = true/false to activate/deactivate task on SchedulingConfiguration class.
//set job time intervals on application.properties.
public class CleaningTask {
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CustomerService customerService;


    @Scheduled(initialDelayString = "PT5S", fixedDelayString = "${cleaningTask.delay}")
    void cleaningTask() throws InterruptedException {
        for (Coupon coupon : couponRepository.findAll()) {
            if (coupon.getEndDate().isBefore(LocalDate.now())) {
                try {
                    customerService.RemoveCouponPurchase(coupon);
                } catch (DoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
                couponRepository.delete(coupon);
                System.out.println("Cleaning Expired Coupons in action : "
                        + coupon.getTitle()
                        + "  has expired, therefore removed from database");
                System.out.println("now is " + new Date());
            }
        }
    }
}