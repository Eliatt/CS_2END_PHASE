package com.eli.coupons_3rd.rest;

import com.eli.coupons_3rd.beans.Coupon;
import com.eli.coupons_3rd.service.CustomerService;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    @SneakyThrows
    @PostMapping("coupon")
    public ResponseEntity<?> purchaseCoupon(@RequestAttribute("service") CustomerService service,
                                            @RequestBody Coupon coupon) {
        service.purchaseCoupon(coupon);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping
    public ResponseEntity<?> getDetails(@RequestAttribute("service") CustomerService service) {
        return new ResponseEntity<>(service.getCustomerDetails(), HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("coupons")
    public ResponseEntity<?> getCustomerCoupons(@RequestAttribute("service") CustomerService service) {
        return new ResponseEntity<>(service.getCustomerCoupons(), HttpStatus.OK);
    }
}

