package com.eli.coupons_2end.service;

import com.eli.coupons_2end.beans.Company;
import com.eli.coupons_2end.beans.Customer;
import com.eli.coupons_2end.exceptions.AlreadyExistException;
import com.eli.coupons_2end.exceptions.DoesNotExistException;
import com.eli.coupons_2end.exceptions.LoginException;
import com.eli.coupons_2end.repository.CompanyRepository;
import com.eli.coupons_2end.repository.CouponRepository;
import com.eli.coupons_2end.repository.CustomerRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy
public class AdminService extends ClientService {

    public AdminService() {
        super();
    }
    @Override
    public boolean login(String email, String password) throws LoginException {
        if (email.equals("admin@admin.com") && password.equals("admin")) {
            System.out.println("Successful login. Returning: ");
            return true;

        }
        throw new LoginException("Failed to Login. Please check and retry ");
    }

    public Company addCompany(Company company) throws AlreadyExistException {
        if (companyRepository.existsByName(company.getName())
                || companyRepository.existsByEmail(company.getEmail())) {
            throw new AlreadyExistException("Company by this name and / or by this email Already exist");
        }
        System.out.println(companyRepository.findAll());
        return companyRepository.save(company);
    }

    public Company updateCompany(Company company) throws DoesNotExistException {
        if (!companyRepository.existsById(company.getId())) {
            throw new DoesNotExistException("Company by this ID Does not exist in order to update");
        }
        return companyRepository.saveAndFlush(company);
    }


    public boolean isCompanyExists(Integer id) {
        return companyRepository.existsById(id);
    }

    public void deleteCompany(Company company) throws DoesNotExistException {
        if (!companyRepository.existsById(company.getId())) {
            throw new DoesNotExistException("Company by this ID does not exist");
        }
        companyRepository.delete(company);
        System.out.println("Company by this ID was deleted from database");
    }

    public Company getOneCompany(Integer id) throws DoesNotExistException {
        if (!companyRepository.existsById(id)) {
            throw new DoesNotExistException("Company by this ID does not exist");
        }
        return companyRepository.getOne(id);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }


    public Customer addCustomer(Customer customer) throws AlreadyExistException {
        if (customerRepository.existsByEmail(customer.getEmail())
                && customerRepository.existsByPassword(customer.getPassword())) {
            throw new AlreadyExistException("Customer Already Exist");
        }
        return customerRepository.save(customer);
    }

    public Customer getOneCustomer(Integer id) throws DoesNotExistException {
        if (!customerRepository.existsById(id)) {
            throw new DoesNotExistException("Customer by this ID does not exist");
        }
        return customerRepository.getOne(id);
    }

    public void updateCustomer(Customer customer) throws DoesNotExistException {
        if (!customerRepository.existsById(customer.getId())) {
            throw new DoesNotExistException("Customer by this ID does not exist");
        }
        customerRepository.saveAndFlush(customer);
    }

    public void deleteCustomer(int customerId) throws DoesNotExistException {
        if (!customerRepository.existsById(customerId)) {
            throw new DoesNotExistException("customer by the ID " + customerId + " does not exist");
        }
        customerRepository.deleteById(customerId);
    }


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


}
