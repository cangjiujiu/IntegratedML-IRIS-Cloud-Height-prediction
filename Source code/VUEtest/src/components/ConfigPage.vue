
<template>
    <div style="width:100%;height: 100%;" class="out in">
        <div style="margin:10px;display:flex;justify-content: center;">
            <div style="width:100%">
                <div style="font-size:20px">{{title}}</div>
                <div style="display:flex;justify-content: center;">
                   <div>
                     <div>
                        <label>{{label[0]}}SOCR-HeightWeight.csv</label> 
                        <input type="file" ref="file" style="" v-on:change="handleFileUpload($event)">
                        <label>{{label[1]}}</label>
                        <input type="radio" name="deletefalg" id="red" v-model="deletefalg" value=1>{{label[2]}}<input value=0 v-model="deletefalg" type="radio" name="deletefalg" id="blue">{{label[3]}}<br>
                     </div>
                     <div>
                        <label>{{label[0]}}Galtons Height Data.csv</label> 
                        <input type="file" ref="file2" style="" v-on:change="handleFileUpload2($event)">
                        <label>{{label[1]}}</label>
                        <input type="radio" name="deletefalg2" id="red2" v-model="deletefalg2" value=1>{{label[2]}}<input value=0 v-model="deletefalg2" type="radio" name="deletefalg2" id="blue2">{{label[3]}}<br>
                
                     </div>
                     <div style="margin-top:10px"><div style="float:right"><label>{{label[4]}}</label></div><div style="float:right"><button  @click="onUpload">{{Button[0]}}</button></div></div>
                     
                  </div>
                
                </div>
          </div>
        </div>
       

    </div>
</template>
<script>
import { formdata } from "../request/api";

export default {
    name: "model1",
    data() { return {
        Language:$mainConfig.language,
        label:["选择","是否删除先前数据","是","否","提交成功后会重新训练模型"],
        Button:["确定"],
        Fheight:78,
        Mheight:66,
        sex:1,
        pHeight:'',
        formData:'',
        deletefalg2:0,
        deletefalg:0,
        file:'',
        file2:'',
        title:'配置',

    }},
    mounted(){
       this.Init()
    },
    methods: {  
        Init(){

          if (this.Language==1)
          {
                 this.title="Config"
                this.label=["choose ","Delete previous data","Y","N","After successful submission, the model will be retrained"]
                 this.Button=["submit"]
           }
     
          },
        // 打开文件
getFile () {
    this.$refs.file.click()
},
// 获取文件
handleFileUpload(event){
    console.log(this.$refs.file.files)
    console.log("??1")
    // 阻止发生默认行为
    event.preventDefault();
    //this.formData = new FormData()
    this.file = this.$refs.file.files[0]
    //this.formData.append('file',file)
    // console.log(formData.get('file'))
    //新增一个按钮确认在上传
    //this.onUpload(formData)
},
handleFileUpload2(event){
    console.log(this.$refs.file2.files)
    console.log("??2")
    // 阻止发生默认行为
    event.preventDefault();
    //this.formData = new FormData()
    this.file2 = this.$refs.file2.files[0]
   // this.formData.append('file',file)
    // console.log(formData.get('file'))
    //新增一个按钮确认在上传
    //this.onUpload(formData)
},
onUpload(){
    this.formData = new FormData()
    this.formData.append('file1',this.file)
    this.formData.append('file2',this.file2)
    this.formData.append('ifdelete1',0)
    this.formData.append('ifdelete2',0)
        formdata(this.formData).then((res) => {
       
        console.log(res); 
          // this.pHeight=re[1]

      })
    },
}
}
</script>