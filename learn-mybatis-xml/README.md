# mybatis
练习mybatis基本用法

## 数据库
场景为 用户, 订单, 地址
- user
- orders
- address


## Roadmap
- [x] 增删改查
- [x] 批量插入
- [x] 分页
- [x] join 查询
- [x] 聚合查询
- [x] group by
- [x] 子查询

## mybatis 结论
```text
1. 查询的字段值需要区分null,空字符串等情况，如果是int等基础类型，还需要注意默认值情况

2. <sql>标签中的字段是可以增加反引号的，防止引入mysql不支持的关键字，如 class,like,order等

3.<result property="id" column="id"> 想通的column可以映射多个不同的property，用来扩展字段

4.对于insert之后，尽量只使用返回的id属性，如果要使用插入后的对象，建议再次查询。数据库在插入的时候可能有些字段有默认值，而不会在
插入之后重新设置到实体类。默认值尽量让mysql自行管理，而不是代码中再次维护。

5.单条插入可以通过对象值为nul动态确定字段要不要设置，批量插入不行，批量插入的值必须是相同字段规格的

5.对于in查询的一点建议，select和delete in的时候先判断一下参数符不符合预期，否则容易查全表或者删除全表

6.association 关联查询，默认开启立即查询关联数据。可通过fetchType="lazy"开启懒加载，或者mybatis全局开启懒加载。懒加载只有用到对应属性才会开始查询。

7.association有两种方式，一种嵌套查询，一种嵌套结果集。建议使用嵌套结果集

8.对于left join，比如查询订单时候，需要查询user信息，xml中user的result不要关联order表的属性，避免出现本来查询结果是null但是却返回了一个id为0的关联对象

9. left join如果关联表有多条的话，结果也是多条

10. 分页

11. 动态查询

12. 注解方式只适合写一些简单的查询，复杂的还是通过xml配置比较好
```

# FAQ
# mybatis FAQ

## dsn后边的参数都有什么作用？

### zeroDateTimeBehavior=CONVERT_TO_NULL

### useAffectedRows=TRUE