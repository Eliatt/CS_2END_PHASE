package com.eli.coupons_3rd.rest;


import com.eli.coupons_3rd.exceptions.LoginException;
import org.reflections.Reflections;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eli.coupons_3rd.login.LoginManager;
import com.eli.coupons_3rd.security.ServiceData;
import com.eli.coupons_3rd.security.TokenManager;
import com.eli.coupons_3rd.service.ClientService;
import lombok.AllArgsConstructor;


import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("login")
@AllArgsConstructor
public class LoginController {
    private static final Set<Class<? extends ClientService>> LOGIN_CLIENT_CLASSES = (new Reflections())
            .getSubTypesOf(ClientService.class);

    private LoginManager loginManager;
    private TokenManager tokenManager;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginCredentials creds) throws LoginException {
        ClientService client;
        for (Class<? extends ClientService> clazz : LOGIN_CLIENT_CLASSES) {
            client = loginManager.login(creds.getEmail(), creds.getPassword(), clazz);
            if (client != null) {
                String token = tokenManager.addService(new ServiceData(client));
                LoginToken loginToken = new LoginToken(token, clazz.getSimpleName().replace("Service", ""));
                return new ResponseEntity<>(loginToken, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("check/{serviceType}")
    public ResponseEntity<?> check(@RequestHeader(name = "Authorization") String token,
                                   @PathVariable String serviceType) {
        System.out.println(String.valueOf(tokenManager.isExist(token)) + " , " + token);
        if (tokenManager.isExist(token)) {
            String bbb = tokenManager.getService(token).getService().getClass().getSimpleName().replace("Service", "");
            System.out.println(bbb.equals(serviceType));
            return new ResponseEntity<>(bbb.equals(serviceType), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("invalid token", HttpStatus.UNAUTHORIZED);
        }

    }

    @DeleteMapping
    public ResponseEntity<?> logout(@RequestHeader(name = "Authorization") String token) {
        tokenManager.removeService(token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
