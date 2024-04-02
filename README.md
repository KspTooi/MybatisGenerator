# 一个可扩展的的Mybatis代码生成器
### 如何使用?  

#### 1.单独使用
拉取项目 运行com.ksptooi.Application类。 参数根据需要更改  
### 示例代码


    public static void main(String[] args) {

        String currentDir = System.getProperty("user.dir");
        File template = DirectoryTools.findTemplate(currentDir, "freemarker");
        assert template!=null;

        MtgDataSource ds = ConfigFactory.datasource()
                .driver("com.mysql.cj.jdbc.Driver")
                .host("127.0.0.1:3306")
                .username("root")
                .password("root")
                .params("?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8")
                .dbName("yourDatabase")
                .templatePath(template.getAbsolutePath())
                .build();

        MtgGenOptions opt = ConfigFactory.config()
                .genController(true)
                .genService(true)
                .withImpl(false)
                .genPo(true)
                .genMapper(true)
                .packetName("com.ksptooi.app")
                .tableName("oauth_clients")
                .projectName("hospital-plugin-service")
                .enableLombok(true)
                .enableSwagger2(true)
                .enableMybatisPlus(true)
                .silence(true)
                .namespace("ns_private")
                .put("private_ns_test","HelloWorld")
                .build();

        MtGenerator mtg = new MtGenerator(ds,opt);
        mtg.generate();
        //mtg.generate();
    }

#### 2.为其他项目生成代码
将该项目放置于其他项目平级的文件夹中,调整projectName启动参数为你的项目文件夹名称。

### 可选配置
Mtg采用自动配置策略,对于没有指定的配置使用约定俗成的自动配置。   
以下是一部分可选的配置

        MtgGenOptions opt = ConfigFactory.config()
                .genController(true)           //可选 是否生成控制器类
                .genService(true)              //可选 是否生成服务类
                .withImpl(false)               //可选 是否使用接口+实现的模式生成服务
                .genPo(true)                   //可选 是否生成PO
                .genVo(true)                   //可选 是否生成VO
                .genMapper(true)               //可选 是否生成Mapper
                .packetName("com.ksptooi.app") //必须 输出包名
                .tableName("your table name")  //必须 数据库表名
                .projectName("projectName")    //可选 项目名称(用于为其他项目生成代码)
                .enableLombok(true)            //可选 启用lombok支持
                .enableSwagger2(true)          //可选 启用swagger2支持
                .enableMybatisPlus(true)       //可选 启用MybatisPlus支持
                .silence(true)                 //可选 控制台屏蔽文件生成日志
                .namespace("ns_private")       //可选 模板的命名空间
                .put("private_ns_test","HelloWorld") //可选 向模板传递自定义参数
                .build();

### 如何自定义
##### 注:该项目提供了一些标准的模板文件位于resources.velocity.命名空间.*.vm
##### 可根据标准模板自定义属于你自己的生成模板
1. 在resources.velocity下创建一个新的文件夹 该文件夹以你的命名空间命名
2. 将标准ns_generic中的标准模板复制到新的文件夹
3. 在标准模板的基础上进行自定义调整
4. 启动生成时将namespace配置为你自己的命名空间



