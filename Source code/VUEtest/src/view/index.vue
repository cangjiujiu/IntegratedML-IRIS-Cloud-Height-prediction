<style>
input[type=text] {
  /* width: 100%;
  padding: 12px 20px;
  margin: 8px 0; */
  width:50%;
  padding: 12px 20px;
  height: 50%;
  box-sizing: border-box;
  
  border-radius: 0.2rem;
}
button{
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 16px 32px;
  text-decoration: none;
  margin: 4px 2px;
  cursor: pointer;
}
.labelclass{
  font-size: 20px;
  height: 30px;

}
.out,.in{

width:300px;

height: 150px;

border:1px solid #BFBFBF;

margin: 20px auto;

}

.out{

box-shadow:0px 0px 10px 5px #aaa;

}

.in{

box-shadow:0px 0px 10px 5px #aaa inset;

}
</style>
<template>
<div>
  <div style="background: #2A3F54;color:white;height:50px;font-size:25px"><div style="margin-left:15px">{{titleList.title}}</div></div>
  <div style="display:flex;justify-content: center;" >
    <div style="width:1080px;">
      <div>
    <select v-model="goodsId" style="width:200px;height: 30px;margin-top: 5px;margin-bottom: 5px;margin-left: 10px" >
<option value="">{{optionChoose}}</option>
<option v-for="item in kindList" v-bind:value="item.id" v-text="item.name" ></option>
</select>
      </div>
      <div :is="currentView" ></div>
  </div>
</div>
  </div>
  

</template>

<script>
//import Home from '../components/home';
import model1 from '../components/model1';
import model2 from '../components/model2';
import ConfigPage from '../components/ConfigPage';
import Tips from '../components/Tips';
import { InitIndex } from "../request/api";
export default {
  name: "index",
  data() {
    return {
      currentView:"",
      goodsId:"",
      kindList:[],
      nowPage:true,
      screenHeight: document.body.clientHeight, //页面高度
      Language:$mainConfig.language,
      titleList:{
       title:"身高预测",
       
      },
      optionChoose:"请选择",
       optionList:[{"name":"体重身高","id":1},{"name":"身高预测","id":2}],
       optionList2:[{"name":"提示","id":4}]
    };
  },
  mounted(){
    //this.getDate()
    this.Init()
  },
  components:{
      //,
      model1,
      model2,
      ConfigPage,
      Tips
  },
  methods: {
  
    Init(){
      ////
      console.log(this.Language);
      if (this.Language==1)
      {
         this.titleList.title="Height prediction"
         this.optionChoose="Please select"
         this.optionList=[{"name":"Weight-Height","id":1},{"name":"Predict height","id":2}]
         
        // [{"name":"Weight-Height","id":1},{"name":"Predict height","id":2},{"name":"Config","id":3}]
         this.optionList2=[{"name":"Tips","id":4}]
      }
      InitIndex().then((res) => {
        console.log(res.data.code)
           if (res.data.code==1)
           {
              //搜索框填写
              this.kindList=this.optionList
              ///this.optionChoose="Please select"

           }
           else
           {

            this.kindList=[]
            this.currentView="Tips"
           }
           //["Height-Weight","Predict height","Config"]
           console.log(this.kindList)

      })
    },
    getDate(){
     
      var _this = this;

      document.title = "身高预测"
 
    }
  },
  watch:{

    goodsId(val,oldvalue){
      if (val==1){this.currentView="model1"}
      else if(val==2){this.currentView="model2"}
      else{
        this.currentView="Tips"

      }
      //this.currentView=val;
      console.log(val);
    }
  }
};
</script>

<style lang='scss' scoped>
$borde: 2px solid #54dcf2;
$juli: -2px;
$headerwidth:600px;
.bgbox {
  width: 100%;
  height: 100%;
  background: #060a23;
  z-index: -1;
  position: absolute;
  left: 0;
  top: 0;
  img {
    width: 100%;
    height: 100%;
  }
}

.content{
  width: 100%;
}
</style>