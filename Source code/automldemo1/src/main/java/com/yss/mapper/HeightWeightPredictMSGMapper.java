package com.yss.mapper;

import com.yss.entity.FamilyMsg;
import com.yss.entity.HeightWeightMsg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface HeightWeightPredictMSGMapper {
    ///创建表
    @Insert("create table IF NOT EXISTS MLTEST_MSG.HeightWeightPredictMSG (Id int primary key identity(101,2),number varchar(50), Height float, Weight  float)")
     void CreateTable();
    @Insert("Insert into MLTEST_MSG.HeightWeightPredictMSG (Height,Weight)values (#{height},#{weight})")
    void InitMSG(HeightWeightMsg h1);

    @Insert("insert into MLTEST_MSG.HeightWeightPredictMSG (Weight)values(#{weight})")
    void inserttoPredict(HeightWeightMsg user);

    @Delete( "delete from MLTEST_MSG.HeightWeightPredictMSG")
    void deletePredict();
    @Select("SELECT  TOP(1) PREDICT(HeightWeightMODEL ) as prediction  FROM  MLTEST_MSG.HeightWeightPredictMSG order BY ID desc")
    Double Predict();
}
