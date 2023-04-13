
<template>
    <div style="width:100%;height: 100%;" class="out in">
        <div style="margin:10px;display:flex;justify-content: center;">
            <div style="width:100%">
                <div style="font-size:20px">{{title}}</div>
                <div style="display:flex;justify-content: center;">
                   <div> 
                            <div style="margin-top:10px"><label >{{label[0]}}（Inches）</label><input type="text" value="" v-model="Fheight"></div>
                            <div style="margin-top:10px"><label >{{label[1]}}（Inches）</label><input type="text" value="" v-model="Mheight"></div>
                            <div style="margin-top:10px"> <label>{{label[2]}}</label> <input type="radio" name="colors" id="red" v-model="sex" value=1>{{label[3]}}<input value=0 v-model="sex" type="radio" name="colors" id="blue">{{label[4]}}<br></div>
                           
                           <div v-for="item in sibling" >
                             <label>{{label[5]}}</label> <input type="radio" v-bind:name="item.id+'sex'" v-bind:id="item.id+'sex'+0" v-model="item.sex" value=1>{{label[3]}}<input value=0 v-model="item.sex" type="radio" v-bind:name="item.id+'sex'" v-bind:id="item.id+'sex'+1">{{label[4]}}<br> 
                             
                             <label>{{label[6]}}（Inches）</label> <input v-bind:id="item.id+2" v-model="item.height"/>
                        
                          </div>
                            <div style="margin-top:10px"><button  @click="tInsert">{{Button[0]}}</button></div>
                            <div style="margin-top:10px"><button  @click="tPredict">{{Button[1]}}</button></div>
                  </div>
                </div>
          </div>
        </div>
        <div style="margin:10px">
            <div style="display:flex;justify-content: center;">
                <div> 
                 {{ pHeight }}
                </div>
           </div>
        </div>
    </div>
</template>
<script>
import { PREDICTHeight } from "../request/api";
export default {
    name: "model1",
    data() { return {
        Fheight:78,
        Mheight:66,
        sex:1,
        pHeight:'',
        sibling:[],
        Language:$mainConfig.language,
        title:"身高预测",
         label:["父亲身高","母亲身高","预测人性别","男","女","性别","身高"],
         Button:["新增","确定"]
    }},

    mounted(){
       this.Init()
    },
    methods: {  
        Init(){

   if (this.Language==1)
   {
       this.title="Predict height"
       this.label=["Father's height","Mother's height","Predictor Gender","Male","Female","gender","Height"]
       this.Button=["add","submit"]
   }
        
},
    tPredict(){
        var temp='{ "type":2, "father":'+this.Fheight+', "mother":'+this.Mheight+', "sex":'+this.sex+',"sibling":'+JSON.stringify(this.sibling)+'}'
        //console.log(temp)
        PREDICTHeight(temp).then((res) => {
          // var re=res.split('return:')
          
           this.pHeight=res.data
           //console.log(re[1]); 

        })
    },
    tInsert(){

        this.sibling.push({id:"test"+(this.sibling.length+1),sex:1,height:70})
    }
}
}
</script>