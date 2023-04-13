# IntegratedML-IRIS-Cloud-Height-prediction

Overview：
With the improvement of living standards, people pay more and more attention to physical health. And the healthy development of children has become more and more a topic of concern for parents. The child's physical development can be reflected from the child's height and weight. Therefore, it is of great significance to predict the height and weight in a timely manner. Pay attention to the child's developmental state through scientific prediction and comparison.
The project uses InterSystems IRIS Cloud SQL to support by entering a large number of weight and height related data, and establishes AutoML based on IntegratedML for predictive analysis. According to the input parent height, it can quickly predict the future height of children, and judge whether the child's body mass index is based on the current height and weight status. In the normal range.

Key Applications: InterSystems IRIS Cloud SQL, IntegratedML


Function：
By applying this program, the height of children in normal developmental state can be quickly predicted. Through the results, parents can judge whether the child's development is normal and whether clinical intervention is required, which will help to understand the child's future height; through the current weight status Determine whether the current child's BMI is normal and understand the child's current health status.


Application Scenario
1. Children's height prediction
2. Monitoring of child development



程序部署
一.数据初始化
1.用sql语句创建表

Program deployment：

Data Initialization
1. Create a table with sql statements


create table IF NOT EXISTS MLTEST_MSG.HeightWeightPredictMSG (Id int primary key identity(101,2),number varchar(50), Height float, Weight  float);
create table IF NOT EXISTS MLTEST_MSG.HeightWeightMSG (Id int primary key identity(101,2),number varchar(50), Height float, Weight  float);
create table IF NOT EXISTS MLTEST_MSG.FamilyMSG(id int primary key identity(101,2),Csex int, Family float,Father float,Mother float, num int,Height float);
create table IF NOT EXISTS MLTEST_MSG.FamilyPredictMSG(id int primary key identity(101,2),Csex int, Family float,Father float,Mother float, num int,Height float);
       ![1](https://user-images.githubusercontent.com/124135718/231637923-69f5e1f7-41a4-4389-9366-62d79edff651.png)

2.将提供的csv文件上传至云数据平台,并按截图所示将数据导入两个表，注意导入时去掉字段ID。

2. Upload the provided csv file to the cloud data platform, and import the data into the two tables as shown in the screenshot. Note that the field ID is removed when importing.
![图片2](https://user-images.githubusercontent.com/124135718/231638013-2a06e658-c959-4a26-97e7-d1a131cb533f.png)

 ![图片3](https://user-images.githubusercontent.com/124135718/231638057-b9e85f72-4974-4726-b14e-8c57e628c195.png)
![图片4](https://user-images.githubusercontent.com/124135718/231638165-4ae188f1-b85e-4ae8-9f7f-bd316f098976.png)

          
二.前端部署
1.记事本打开distML文件夹下的config.js文件，修改信息后启动前端页面（可以用Tomcat）

Front-end deployment
1. Notepad Open the config.js file under the distML folder, modify the information and start the front-end page (you can use Tomcat)

![图片5](https://user-images.githubusercontent.com/124135718/231638315-5b860f04-cd3b-4845-a24c-40ba959a57d6.png)


 
①:后端路径 
②:为1时页面显示为英文，0时是中文

①: Backend path 
②: When it is 1, the page is displayed in English, and when it is 0, it is Chinese.

三．后端部署
打开后端包中application.yml文件修改文件中数据库信息

Back-end deployment
Open the application.yml file in the backend package and modify the database information in the file

![图片6](https://user-images.githubusercontent.com/124135718/231639752-20d3eebd-588a-4928-a171-f04b9ce914f6.png)


①：数据库url
②：用户名
③：密码
然后用java命令启动automldemo1.jar包,默认端口号为9999

①: database url
②: Username
③: Password

Then start the automldemo1.jar package with the java command, the default port number is 9999

![图片7](https://user-images.githubusercontent.com/124135718/231639789-5650b1d4-460f-433a-ba0f-29b798a52648.png)


启动之后进入前端页面（如果用tomcat启动地址为:  ip:port/distML）
初次进入页面会创建，训练模型，需要等待一会。也可以在步骤一数据初始化创建完表之后通过以下语句创建模型

After starting, enter the front-end page (if you use tomcat to start the address: ip: port/distML)
The first time you enter the page, the model will be created and trained, and you need to wait for a while. You can also create a model with the following statement after the data initialization and table creation in step 1.

Create model FamiyModel predicting (Height) from MLTEST_MSG.FamilyMSG
Train model FamiyModel
Create model HeightWeightMODEL predicting (Height) WITH (Weight float)  from MLTEST_MSG.HeightWeightMSG
Train model HeightWeightMODEL
![图片8](https://user-images.githubusercontent.com/124135718/231639975-240bde62-bd18-4932-8096-d06cc6438a20.png)


使用流程

①选择模块
②填写相关数据，若有成年兄弟姐妹数据可点击add填写信息
③点击提交，等待一会出现预测结果
![图片9](https://user-images.githubusercontent.com/124135718/231639991-6c8a5367-abd8-4a01-b69a-c4a3485419c3.png)
![图片10](https://user-images.githubusercontent.com/124135718/231640020-c6dbb27c-5098-49dc-b2d5-c9a2444358de.png)

