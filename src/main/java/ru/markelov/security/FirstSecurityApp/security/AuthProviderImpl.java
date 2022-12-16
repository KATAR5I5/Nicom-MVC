package ru.markelov.security.FirstSecurityApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.markelov.security.FirstSecurityApp.services.EmployeeDetailService;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    private EmployeeDetailService employeeDetailService;

    @Autowired
    public AuthProviderImpl(EmployeeDetailService employeeDetailService) {
        this.employeeDetailService = employeeDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails employeeDetails = employeeDetailService.loadUserByUsername(username);
        String password = authentication.getCredentials().toString();
        if (!password.equals(employeeDetails.getPassword())) {
            throw  new BadCredentialsException("Некорректный пароль");
        }

        return new UsernamePasswordAuthenticationToken(employeeDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
