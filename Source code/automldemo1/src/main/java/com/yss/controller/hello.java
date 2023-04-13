package com.yss.controller;

import com.alibaba.fastjson.JSONObject;
import com.yss.entity.FamilyMsg;
import com.yss.entity.HeightWeightMsg;
import com.yss.mapper.FamilyMSGMapper;
import com.yss.mapper.FamilyPredictMSGMapper;
import com.yss.mapper.HeightWeightMSGMapper;
import com.yss.mapper.HeightWeightPredictMSGMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@RestController
@RequestMapping("/AutoML")
@CrossOrigin(origins = "*")
public class hello {
    @Autowired
    private HeightWeightMSGMapper hw;
    @Autowired
    private HeightWeightPredictMSGMapper hwp;
    @Autowired
    private FamilyMSGMapper fm;
    @Autowired
    private FamilyPredictMSGMapper fpm;
    @GetMapping("/Init")
    public String Init() {
        ///判断是否存在对应模型及训练是否完成
       /* ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        HeightWeightMSGMapper hw=(HeightWeightMSGMapper) context.getBean("heightWeightMSGMapper");
        int t=hw.queryModel();
        if (t==0)
        {
            hw.creatModel();
            hw.trainModel();

        }*/
       // ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        //HeightWeightMSGMapper hw=(HeightWeightMSGMapper) context.getBean("heightWeightMSGMapper");
        int t=hw.queryModel();
        int t2=fm.queryModel();
        int code=1;
        if (t==0)
        {
            hw.creatModel();
            hw.trainModel();
            code=0;
        }
        if (t2==0)
        {
            fm.creatModel();
            hw.trainModel();
            code=0;
        }
       // code=0;
        return "{\"code\":"+code+"}";
        /*ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        HeightWeightMSGMapper hw=(HeightWeightMSGMapper) context.getBean("heightWeightMSGMapper");
        hw.CreateTable();
        HeightWeightPredictMSGMapper h1=(HeightWeightPredictMSGMapper) context.getBean("heightWeightPredictMSGMapper");
        h1.CreateTable();*/
        /*InputStream path = this.getClass().getResourceAsStream("/csv/SOCR-HeightWeight.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(path));
        BufferedReader reader2 = reader;
        String str="";
        try {

            //long length=reader2.lines().count();
            long length=26000;
            //System.out.println(length);
            //reader.reset();
            str=reader.readLine();
            while (str != null) {
                String[]  strs=str.split(",");

                try {
                    Double.parseDouble(strs[1]);
                    Double.parseDouble(strs[2]);
                    HeightWeightMsg hw1=new HeightWeightMsg();
                    hw1.setHeight(Double.parseDouble(strs[1]));
                    hw1.setWeight(Double.parseDouble(strs[2]));
                    hw.InitMSG(hw1);

                } catch (NumberFormatException e) {
                   //标题行
                }
                str=reader.readLine();
                System.out.println(str);
            }



            //reader.lines();

        } catch (IOException e) {
            e.printStackTrace();
        }
       */
       // return "--";
    }
        ////身高预测后端代码
        @PostMapping("/PREDICTHeight")
        public Double PREDICTHeight(@RequestBody String a1) {
            Object a11=JSONObject.parseObject(a1);
            //System.out.println(((JSONObject) a11).get("height"));
            long csex=((JSONObject) a11).getLong("sex");
            long Father=((JSONObject) a11).getLong("father");
            long Mother=((JSONObject) a11).getLong("mother");
            //ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
            FamilyMsg user1=new FamilyMsg();
            user1.setCsex(csex);

            user1.setFather(Father);
            user1.setMother(Mother);

            //FamilyMSGMapper f1=//(FamilyMSGMapper) context.getBean("familyMSGMapper");
            //f1=fm;
            //f1.
            ///先获取
            long family=fm.quaryFamilyNum()+1;
            user1.setFamily(family);
           // FamilyPredictMSGMapper familyPredictMSGMapper=(FamilyPredictMSGMapper) context.getBean("familyPredictMSGMapper");
            fpm.deletePredict();
            fpm.inserttoPredict(user1);
            //添加至原表
            List<Object> l1=((JSONObject) a11).getJSONArray("sibling");
            int num=l1.size()+1;
            user1.setNum(num);
            l1.forEach(item-> {
                FamilyMsg userTemp=new FamilyMsg();
               Object temp=JSONObject.parseObject(item.toString());
                //Object t2=new JSONObject();
               // t2=item;
                //Object t=item;
               // t.getClass();
                userTemp.setHeight(((JSONObject) temp).getDouble("height"));
                userTemp.setCsex(((JSONObject) temp).getLong("sex"));
                //System.out.println(item.toString());
                userTemp.setFamily(family);
                userTemp.setFather(Father);
                userTemp.setMother(Mother);
                userTemp.setNum(num);
                fm.inserttoMsg(userTemp);

                ///然后再训练
               // userTemp.setCsex();
               // System.out.println(JSONObject.parseObject(item.toString(),FamilyMsg.class).getHeight());
                   // userTemp.setCsex((JSONObject)item.getc);
                            //System.out.println(item);

                    });
                    //训练

            ///预测
            Double temp= fpm.Predict();
            System.out.println(temp);


            return temp;
        }
       @PostMapping("/PREDICTHeightWeight")
       public Double PREDICTHeightWeight(@RequestBody String a1) {
           Object a11=JSONObject.parseObject(a1);
           //System.out.println(((JSONObject) a11).get("height"));
           long weight=((JSONObject) a11).getLong("weight");
           //ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
           //HeightWeightMSGMapper hwm=(HeightWeightMSGMapper) context.getBean("heightWeightMSGMapper");
           //HeightWeightPredictMSGMapper hwpm=(HeightWeightPredictMSGMapper) context.getBean("heightWeightPredictMSGMapper");
           HeightWeightMsg hw1=new HeightWeightMsg();
           hw1.setWeight(weight);
           hwp.deletePredict();
           hwp.inserttoPredict(hw1);
           double re=hwp.Predict();
           ///获取入参

        return re;

       }
}
