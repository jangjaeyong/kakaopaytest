package com.test.kakaopaytest.config;

import com.test.kakaopaytest.test.DbTest;
import com.test.kakaopaytest.test.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginMapper {
    AdminAccount findByUserid(String userId);
}
