package com.yss.mapper;

import com.yss.entity.FamilyMsg;
import com.yss.entity.HeightWeightMsg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface HeightWeightMSGMapper {

    @Insert("create table IF NOT EXISTS MLTEST_MSG.HeightWeightMSG (Id int primary key identity(101,2),number varchar(50), Height float, Weight  float)")
     void CreateTable();

    @Insert("Insert into MLTEST_MSG.HeightWeightMSG (Height,Weight)values (#{height},#{weight})")
    void InitMSG(HeightWeightMsg h1);
    @Select("select count(1) as num from INFORMATION_SCHEMA.ML_MODELS WHERE MODEL_NAME='HeightWeightMODEL' and DEFAULT_TRAINED_MODEL_NAME !='null'")
    int queryModel();
    @Update("Create model HeightWeightMODEL predicting (Height) WITH (Weight float)  from MLTEST_MSG.HeightWeightMSG")
    void creatModel();
    @Update("Train model HeightWeightMODEL")
    void trainModel();

}
