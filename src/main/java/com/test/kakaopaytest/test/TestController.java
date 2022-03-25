package com.test.kakaopaytest.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/api/chkAuthData")
    public ResponseTemplate chkAuthData(@RequestBody Test test){
        ResponseTemplate responseTemplate;
        try {
            boolean result = testService.chkAuthData(test);
            responseTemplate = ResponseTemplate.builder().result(result).build();
        }catch (Exception e){
            responseTemplate = ResponseTemplate.builder().result(false).data(e.getMessage()).build();
        }


        return responseTemplate;
    }

    @PutMapping("/api/confirmRequest")
    public ResponseTemplate confirmRequest(@RequestBody Test test){
        ResponseTemplate responseTemplate;
        try {
            String resUserId = testService.selectResUserId(test);
            if(resUserId==null || resUserId.equals("")) {
                testService.updateResUserId(test);
                responseTemplate = ResponseTemplate.builder().result(true).build();
            }else{
                responseTemplate = ResponseTemplate.builder().result(true).data(resUserId).build();
            }
        }catch (Exception e){
            responseTemplate = ResponseTemplate.builder().result(false).data(e.getMessage()).build();
        }


        return responseTemplate;
    }

    @PutMapping("/api/responseSave")
    public ResponseTemplate responseSave(@RequestBody Test test){
        ResponseTemplate responseTemplate;
        try {
            testService.updateResContents(test);
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

    @GetMapping("/api/getDataDetail")
    public ResponseTemplate getDataDetail(int seq){
        ResponseTemplate responseTemplate;
        try {
            Test detail = testService.getDataDetail(seq);
            responseTemplate = ResponseTemplate.builder().result(true).data(detail).build();
        }catch (Exception e){
            responseTemplate = ResponseTemplate.builder().result(false).data(e.getMessage()).build();
        }


        return responseTemplate;
    }









}
