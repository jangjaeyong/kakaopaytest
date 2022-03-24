package com.test.kakaopaytest.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepositoy testRepositoy;

    public List<DbTest> dbTest(){
        return testRepositoy.dbTest();
    }

    public void saveData(Test test){
        testRepositoy.saveData(test);
    }

    public List<Test> getData(){
        return testRepositoy.getData();
    }
}
