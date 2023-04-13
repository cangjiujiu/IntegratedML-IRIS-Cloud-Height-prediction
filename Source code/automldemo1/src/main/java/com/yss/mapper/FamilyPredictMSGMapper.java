package com.yss.mapper;

import com.yss.entity.FamilyMsg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FamilyPredictMSGMapper {


    @Select("select top 1 * from MLTEST_MSG.FamilyMSG ")
    List<FamilyMsg> quaryAll();
    @Select("SELECT  TOP(1) PREDICT(FamiyModel ) as prediction  FROM  MLTEST_MSG.FamilyPredictMSG order BY ID desc")
    Double Predict();

    @Insert("insert into MLTEST_MSG.FamilyPredictMSG (Family,Father,Mother,Csex,num)values(#{family},#{father},#{mother},#{csex},#{num})")
    void inserttoPredict(FamilyMsg user);

     @Delete( "delete from MLTEST_MSG.FamilyPredictMSG")
     void deletePredict();
}
