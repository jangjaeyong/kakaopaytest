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

    public Test getDataDetail(int seq){
        return testRepositoy.getDataDetail(seq);
    }

    public boolean chkAuthData(Test test){
        return testRepositoy.chkAuthData(test)>0? true: false;
    }

    public String selectResUserId(Test test){
        return testRepositoy.selectResUserId(test);
    }

    public void updateResUserId(Test test){
        testRepositoy.updateResUserId(test);
    }
    public void updateResContents(Test test){
        testRepositoy.updateResContents(test);
    }
}
