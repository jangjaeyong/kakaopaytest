package com.test.kakaopaytest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService  implements UserDetailsService {

    @Autowired
    private LoginRepositoy loginRepositoy;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException, NullPointerException{
        AdminAccount account = null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        try {
            System.out.println("userId:"+userId);

        //    System.out.println("~~~"+passwordEncoder.encode("1234"));
            account = loginRepositoy.findByUserid(userId);

            if(account == null) {
                throw new UsernameNotFoundException("아이디와 비밀번호를 다시 확인해주세요.");
            }

            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            return new User(userId, "{SHA-256}"+account.getUserPw(), authorities);
        } catch(NullPointerException e) {
            System.out.println("아이디와 비밀번호를 다시 확인해주세요.");
            return null;
        }

    }
}
