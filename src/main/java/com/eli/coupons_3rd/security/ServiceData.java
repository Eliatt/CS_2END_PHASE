package com.eli.coupons_3rd.security;


import com.eli.coupons_3rd.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class ServiceData {

    Date created;
    ClientService service;

    public ServiceData(ClientService service) {
        this.service = service;
        this.created = new Date(System.currentTimeMillis());
    }
}
