package com.test.kakaopaytest.config;

import com.test.kakaopaytest.test.DbTest;
import com.test.kakaopaytest.test.Test;
import com.test.kakaopaytest.test.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginRepositoy {
    @Autowired
    private LoginMapper loginMapper;

    public AdminAccount findByUserid(String userId){
        return loginMapper.findByUserid(userId);
    };

}
