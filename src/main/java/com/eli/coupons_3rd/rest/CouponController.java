package com.eli.coupons_3rd.rest;


import com.eli.coupons_3rd.beans.Coupon;
import com.eli.coupons_3rd.exceptions.FailOperationException;
import com.eli.coupons_3rd.repository.CouponRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CouponController {

    private CouponRepository couponRepository;

    @SneakyThrows
    @GetMapping("coupons/{page}")
    public ResponseEntity<?> getAllCoupons(@PathVariable(required = false) Integer page,
                                           @RequestParam(defaultValue = "5") int count) {
        if (page == null || page < 1) {
            throw new FailOperationException("Page Error");
        }
        Page<Coupon> coupons = couponRepository.findAll(PageRequest.of(page - 1, count));
        return new ResponseEntity<>(coupons.getContent(), HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("couponsCount")
    public ResponseEntity<?> getAllCoupons() {
        return new ResponseEntity<>(couponRepository.count(), HttpStatus.OK);
    }

}
