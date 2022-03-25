package com.test.kakaopaytest.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestRepositoy {
    @Autowired
    private TestMapper testMapper;

    public List<DbTest> dbTest(){
        return testMapper.selectTest();
    }

    public void saveData(Test test){
        testMapper.saveData(test);
    }

    public List<Test> getData(){
        return testMapper.getData();
    }

    public Test getDataDetail(int seq){
        return testMapper.getDataDetail(seq);
    }

    public int chkAuthData(Test test){
        return testMapper.chkAuthData(test);
    }

    public String selectResUserId(Test test){
        return testMapper.selectResUserId(test);
    }

    public void updateResUserId(Test test){
        testMapper.updateResUserId(test);
    }

    public void updateResContents(Test test){
        testMapper.updateResContents(test);
    }
}
