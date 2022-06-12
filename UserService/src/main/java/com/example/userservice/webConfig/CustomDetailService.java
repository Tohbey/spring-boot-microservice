package com.example.userservice.webConfig;

import com.example.userservice.dao.UserDao;
import com.example.userservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomDetailService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public CustomDetail loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = this.userDao.findByEmail(email);
        CustomDetail userDetail = null;
        if (user.isPresent()) {
            userDetail = new CustomDetail();
            userDetail.setUser(user.get());
        } else {
            throw new UsernameNotFoundException("user not exist with email: " + email);
        }
        return userDetail;
    }
}
