package com.test.kakaopaytest.test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.*;

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
                .addFilter(new CharacterEncodingFilter("utf-8",true))// ?????? ?????? ?????????
                .alwaysDo(print())
                .build();
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("????????? ?????? ?????????")
    void saveData() {
        com.test.kakaopaytest.test.Test test = com.test.kakaopaytest.test.Test.builder()
                .userId("test1111")
                .userPw("1234")
                .title("??????????????????.")
                .contents("?????????1\n?????????2\n?????????3")
                .build();
        testService.saveData(test);
    }

    @Test
    @DisplayName("????????? ?????? ??????")
    void chkAuthData() {
        com.test.kakaopaytest.test.Test test = com.test.kakaopaytest.test.Test.builder()
                .userId("test1111")
                .userPw("1234")
                .seq(4)
                .build();
        boolean result = testService.chkAuthData(test);
        assertThat(result).isEqualTo(true);
    }

    @Test
    @Transactional
    @Rollback
    void confirmRequest() {
        com.test.kakaopaytest.test.Test test = com.test.kakaopaytest.test.Test.builder()
                .resUserId("testAdmin")
                .seq(4)
                .build();
        String resUserId = testService.selectResUserId(test);
        assertThat(resUserId).isNull();

        if(resUserId==null || resUserId.equals("")) {
            //???????????? ??????
            testService.updateResUserId(test);

            //????????? ????????? ??? ???????????? ?????? ??????
            resUserId = testService.selectResUserId(test);
            assertThat(resUserId).isNotNull();
        }
    }

    @Test
    @Transactional
    @Rollback
    void responseSave() {
        com.test.kakaopaytest.test.Test test = com.test.kakaopaytest.test.Test.builder()
                .resContents("testAdminContents")
                .seq(4)
                .build();
        testService.updateResContents(test);
    }

    @Test
    void getData() {
        List<com.test.kakaopaytest.test.Test> list = testService.getData();
        assertThat(list).isNotEmpty();
    }

    @Test
    void getDataDetail() {
        int seq=4;
        com.test.kakaopaytest.test.Test detail = testService.getDataDetail(seq);
        assertThat(detail.getUserId()).isEqualTo("test1111");

    }
}