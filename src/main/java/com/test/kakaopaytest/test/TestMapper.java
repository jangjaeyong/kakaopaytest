package com.test.kakaopaytest.test;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<DbTest> selectTest();
    void saveData(Test test);
    List<Test> getData();
    Test getDataDetail(int seq);
    int chkAuthData(Test test);
    String selectResUserId(Test test);
    void updateResUserId(Test test);
    void updateResContents(Test test);
}
