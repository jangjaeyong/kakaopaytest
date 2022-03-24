package com.test.kakaopaytest.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
@RestController
public class TestController {

    @Autowired
    private TestService testService;


    @GetMapping("dbtest")
    public List<DbTest> dbtest(){
        return testService.dbTest();
    }

    @PostMapping("/api/saveData")
    public ResponseTemplate saveData(@RequestBody Test test){
        ResponseTemplate responseTemplate;
        try {
            testService.saveData(test);
            responseTemplate = ResponseTemplate.builder().result(true).build();
        }catch (Exception e){
            responseTemplate = ResponseTemplate.builder().result(false).data(e.getMessage()).build();
        }


        return responseTemplate;
    }

    @GetMapping("/api/getData")
    public ResponseTemplate getData(){
        ResponseTemplate responseTemplate;
        try {
            List<Test> list = testService.getData();
            responseTemplate = ResponseTemplate.builder().result(true).data(list).build();
        }catch (Exception e){
            responseTemplate = ResponseTemplate.builder().result(false).data(e.getMessage()).build();
        }


        return responseTemplate;
    }









}
