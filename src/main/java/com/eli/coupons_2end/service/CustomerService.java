package com.eli.coupons_2end.service;

import com.eli.coupons_2end.beans.Coupon;
import com.eli.coupons_2end.beans.Customer;
import com.eli.coupons_2end.exceptions.FailOperationException;
import com.eli.coupons_2end.exceptions.LoginException;
import com.eli.coupons_2end.repository.CompanyRepository;
import com.eli.coupons_2end.repository.CouponRepository;
import com.eli.coupons_2end.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService extends ClientService {


    public CustomerService(CompanyRepository companyRepository,
                           CustomerRepository customerRepository,
                           CouponRepository couponRepository) {
        super(companyRepository,
                customerRepository,
                couponRepository);
    }

    private int customerId;


    @Override
    public boolean login(String email, String password) throws LoginException {
        for (Customer customer : customerRepository.findAll()) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                this.customerId = customer.getId();
                return true;
            }
        }
        throw new LoginException("Fail Login attempt");
    }

    public Customer getCustomer() {
        Customer toGet = customerRepository.getOne(this.customerId);
        return toGet;
    }

    public boolean AddCouponPurchase(Coupon coupon) throws FailOperationException {
        for (Coupon toAdd : getCustomerCoupons()) {
            if (toAdd.getId() == couponRepository.getByCompanyIdAndTitle(coupon.getCompanyId(), coupon.getTitle()).getId()) {
                throw new FailOperationException(
                        "Customer Already purchased this coupon");
            }
        }
        if (coupon.getAmount() == 0) {
            throw new FailOperationException("This coupon out of stock");

        } else if (coupon.getEndDate().isBefore(LocalDate.now())) {
            throw new FailOperationException("This coupon has expired");

        } else {
            Coupon toAdd = couponRepository.getByCompanyIdAndTitle(coupon.getCompanyId(), coupon.getTitle());
            toAdd.setAmount(toAdd.getAmount() - 1);
            couponRepository.saveAndFlush(toAdd);
            Customer customer = customerRepository.getOne(this.customerId);
            customer.getCoupons().add(coupon);
            customerRepository.saveAndFlush(customer);
            return true;
        }
    }

    public List<Coupon> getCustomerCoupons() {
        return getCustomer().getCoupons();
    }


}
