<style>
input[type=text] {
  /* width: 100%;
  padding: 12px 20px;
  margin: 8px 0; */
  width:50%;
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
    <div style="width:100%;height: 100%;" class="out in">
        <div style="margin:10px;display:flex;justify-content: center;">
            <div style="width:100%">
                <div style="font-size:20px">{{ title }}</div>
                    <div style="display:flex;justify-content: center;">
                        <div> 
                           <label class="labelclass">{{label[0]}}（Pounds）</label><input type="text" value="130" v-model="weight">
                           <div style="margin-top:10px"><div style="float:right"><button  @click="tPredict">{{ Button }}</button></div></div>
                         </div>
                    </div>
              </div>
        </div>
        <div style="margin:10px">
            <div style="display:flex;justify-content: center;">
                        <div> 
                           {{pHeight}}
                        </div>
                        </div>
                    </div>
    </div>
</template>
<script>
import { PREDICTHeightWeight } from "../request/api";
export default {
    name: "model1",
    data() { return {
      Language:$mainConfig.language,
         weight:"130",
         pHeight:"",
         title:"体重-身高",
         label:["请输入体重"],
         Button:"确定"
    }},
    mounted(){
       this.Init()
    },
    methods: {  
      Init(){

      if (this.Language==1)
      {
         this.title="Weight-Height"
         this.label=["Please enter your weight"]
         this.Button="submit"
      }

      },
    tPredict(){
        var temp='{ "type":1, "weight":'+this.weight+'}'
        PREDICTHeightWeight(temp).then((res) => {
        // var re=res.split('return:')
        //console.log(re); 
           this.pHeight=res.data

      })
    },
}
}
</script>