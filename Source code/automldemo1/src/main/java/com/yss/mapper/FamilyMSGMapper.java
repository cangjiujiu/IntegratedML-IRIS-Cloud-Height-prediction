package com.yss.mapper;

import com.yss.entity.FamilyMsg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FamilyMSGMapper {
    @Select("select top 1 * from MLTEST_MSG.FamilyMSG ")
    List<FamilyMsg> quaryAll();
    @Select("select count(1) as num from MLTEST_MSG.FamilyMSG ")
    int quaryCount();
    @Select("select top 1 Family  from MLTEST_MSG.FamilyMSG Order BY ID desc ")
    int quaryFamilyNum();

    @Insert("insert into MLTEST_MSG.FamilyMSG (Family,Father,Mother,Csex,num)values(#{family},#{father},#{mother},#{csex},#{num})")
    void inserttoMsg(FamilyMsg user);
    @Select("select count(1) as num from INFORMATION_SCHEMA.ML_MODELS WHERE MODEL_NAME='FamiyModel' and DEFAULT_TRAINED_MODEL_NAME !='null'")
    int queryModel();
    @Update("Create model FamiyModel predicting (Height) from MLTEST_MSG.FamilyMSG")
    void creatModel();
    @Update("Train model FamiyModel")
    void trainModel();

}
