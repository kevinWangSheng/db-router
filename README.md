# db-router-starter
一个基于注解+反射+aop+mybatis拦截器+散列哈希完成的基于对应字段的分库分表组件

## 使用

@DBRouter对应的key指定对应的散列哈希的计算，根据计算出的值和你给出的配置库信息进行哈希映射，散列到对应的哈希槽中。

@DBRouterStrategy(splitTable = true)表示分表，就是一个库中有多个相同结构不同名字的表。注意，这里的表需要相同的前缀+001、002这种形式



基本的配置：

```yaml
mini-db-router:
  jdbc:
    datasource:
      dbCount: 2
      tbCount: 4
      default: db00
      routerKey: uId
      list: db01,db02
      db00:  #数据库1
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://${你的数据库ip}:3306/lottery?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8
        username: root
        password: 13379895997.hui
      db01:  # 数据库2
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://${你的数据库ip}:3306/lottery_01?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8
        username: root
        password: 13379895997.hui
      db02:  # 数据库3  注意，这里的具体数据库名称在你的url进行对应的配置
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://${你的数据库ip}:3306/lottery_02?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8
        username: root
        password: 13379895997.hui
```

使用案例： 如果只是分表就只使用@DBRouterStrategy(splitTable = true) 即可，相同如果只分库就只使用@DBRouter(key = "uid")即可，这里的uid表示的是你对应的字段的名字，他会通过反射进行获取的。

如果两个都需要使用的话，对配置上注解即可

```java
@Mapper
@DBRouterStrategy(splitTable = true)  // 表示分表，相同结构，相同前缀的表名
public interface UserStrategyExportMapper extends BaseMapper<UserStrategyExport> {

    @DBRouter(key = "uid")  // 分库，key是表示你用来分库进行散列计算的字段
    UserStrategyExport selectByUserId(String userId);

    @DBRouter(key = "uid")
    void insertSelective (UserStrategyExport userStrategyExport);
}
```

