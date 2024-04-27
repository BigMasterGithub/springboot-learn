@Validated使用方式
（1）对于controller接口中基本类型的参数，需要在Controller类上标明@Validated
（2）对于controller接口中对象类型的参数，需要在参数前加@Validated，Controller类不需要添加该注解

以上两种情况，都不需要在方法加@Validated注解。

springboot 修改配置属性的四种方式
（1）在resources下的application.yaml中修改属性
（2）在jar包所在的目录下的application.yaml中修改属性
（3） 在电脑的环境变量中进行设置。 比如在windows的环境变量中直接添加 server.port 和 7777
（4）在命令行处添加属性 。例如java -jar xxx.jar --server.port=8888
优先级从高到低为：(4) > (3)> (2) >(1)

springboot thymeleaf相关笔记
1. resources/templates 中存放html页面，用于映射controller方法中的返回页面。
2. 如何区分静态资源路径和servlet请求？ 在application。yaml中设置   mvc.static-path-pattern的值。
   比如修改为 /musads/** ，那么所有请求以 /musads/ 开头的请求都会被当做成静态资源请求，并且资源的位置默认在 resources/static/下。
3. springboot 默认是配置了 "/"  ---->  "resources/templates/index.html"的映射，所以可以直接在这个目录中添加index.html。

mybatis-plus 学习笔记
1. 高版本springboot 3.x.x 搭配mybatis-plus时各种报错，建议降低springboot版本为2.x.x系列。


ThreadLocal、JWT校验学习笔记


@JsonIgnore和@JsonFormat用法:
1. @JsonIgnore可以标记在实体类的敏感属性中，当对象序列化时，忽略该注解标记的属性
2. @JsonFormat 用于反序列化时，制定json中的属性与对象属性的匹配规则，一般用于时间类型的属性上。
   例如：json中的 2024=09=24 可以用 yyyy=MM=dd 进行匹配

Springboot 多环境的配置方法
（1）方法一：在application.yaml中配置全部环境属性
1. 使用---分割多个配置环境
2. 为每个环境添加 名称 on-profile：xxx
3. 使用spring.profile.active:xxx 进行激活对应的环境配置信息
（2）方法二：在不同配置文件中配置每个环境属性
1. application-xxx.yaml 为xxx环境的属性信息
2. 在application.yaml中激活对应的环境信息
（3）方法三：在方法二的基础上为各个环境进行分组配置。