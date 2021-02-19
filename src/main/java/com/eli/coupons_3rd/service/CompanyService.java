package com.eli.coupons_3rd.service;

import com.eli.coupons_3rd.beans.Category;
import com.eli.coupons_3rd.beans.Company;
import com.eli.coupons_3rd.beans.Coupon;
import com.eli.coupons_3rd.exceptions.DoesNotExistException;
import com.eli.coupons_3rd.exceptions.FailOperationException;
import com.eli.coupons_3rd.exceptions.LoginException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
public class CompanyService extends ClientService {


    private int companyID;
    private Company company;
    private Coupon coupon;


    public CompanyService() {
        super();
    }


    @Override
    public boolean login(String email, String password) throws LoginException {
        for (Company company : companyRepository.findAll()) {
            if (company.getEmail().equals(email) && company.getPassword().equals(password)) {
                this.companyID = company.getId();
                return true;
            }
        }
        throw new LoginException("Fail Login attempt");
    }


    public void addCoupon(Coupon coupon) throws FailOperationException {

        for (Coupon toSave : couponRepository.findAll()) {
            if (toSave.getCompanyId() == this.companyID) {
                if (toSave.getTitle().equals(coupon.getTitle())) {
                    throw new FailOperationException("Error: Check companyID and/or coupon existence in database  ");
                }
            }
        }
        couponRepository.save(coupon);
        Company company = companyRepository.getOne(this.companyID);
        company.getCoupons().add(coupon);
        companyRepository.saveAndFlush(company);
        System.out.println("Coupon " + coupon.getTitle() + " Added");

    }

    public void updateCoupon(Coupon coupon) throws DoesNotExistException {
        if (!couponRepository.existsById(coupon.getId())) {
            throw new DoesNotExistException("Coupon by this ID does not exist");
        }
        System.out.println("Coupon " + coupon.getTitle() + " Successfully updated");
        couponRepository.saveAndFlush(coupon);
    }

    public void deleteCoupon(Coupon coupon) {
        couponRepository.delete(coupon);
    }

    public void deleteCoupon(int couponID) {
        Coupon coup = couponRepository.getOne(couponID);
        if (coup.getId() == companyID) {
            couponRepository.deleteById(couponID);
        }
    }

    public Coupon getCoupon(int couponId) throws DoesNotExistException {
        if (couponRepository.existsById(couponId)) {
            System.out.println(couponRepository.getOne(couponId));
        } else {
            throw new DoesNotExistException("Coupon does not exist");
        }
        System.out.println("Coupon found. Enable further actions");
        return coupon;
    }


    public List<Coupon> getCompanyCoupons() {
        return companyRepository.getOne(this.companyID).getCoupons();
    }

    public List<Coupon> getCompanyCoupons(Category category) {
        List<Coupon> coupons = getCompanyCoupons();
        List<Coupon> result = new ArrayList<Coupon>();
        for (Coupon coupon : coupons) {
            if (coupon.getCategory().equals(category)) {
                result.add(coupon);
            }
        }
        return result;
    }

    public List<Coupon> getCompanyCoupons(double maxPrice) {
        List<Coupon> coupons = getCompanyCoupons();
        List<Coupon> result = new ArrayList<Coupon>();
        for (Coupon coupon : coupons) {
            if (coupon.getPrice() <= maxPrice) {
                result.add(coupon);
            }
        }
        return result;
    }

    public Company getCompanyDetails() {
        return companyRepository.getOne(this.companyID);
    }


}
