
<template>
    <div style="width:100%;height: 100%;" class="out in">
        <div style="margin:10px;display:flex;justify-content: center;">
            <div style="width:100%">
                <div style="font-size:20px">{{title}}</div>
                <div style="display:flex;justify-content: center;">
                   <div>
                    {{label[0]}}
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
        label:["请先完成数据初始化"],
        title:"提示"
       

    }},
    mounted(){
       this.Init()
    },
    methods: {  
        Init(){

          if (this.Language==1)
          {
                this.title="Tips"
                this.label=["Please complete data initialization first"]
                 //this.Button=["submit"]
           }
     
          },
        // 打开文件

    

}
}
</script>