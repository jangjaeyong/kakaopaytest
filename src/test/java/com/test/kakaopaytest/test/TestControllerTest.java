package com.test.kakaopaytest.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class TestControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    private TestService testService;

    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilter(new CharacterEncodingFilter("utf-8",true))// 한글 꺠짐 떄문에
                .alwaysDo(print())
                .build();
    }
    @Test
    @Transactional
    @Rollback(true)
    void saveData() {

      //  testService.saveData(test);
    }

    @Test
    void chkAuthData() {
    }

    @Test
    void confirmRequest() {
    }

    @Test
    void responseSave() {
    }

    @Test
    void getData() {
    }

    @Test
    void getDataDetail() {
    }
}