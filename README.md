# spring-vue-docker

## 无聊上点自己的demo   现在属于试验阶段  代码很乱

根据资料和自己经验 做一些比较 实用的 demo

舍弃spring boot 模版化的web 开发  不能通用工程化的技术价值不大(个人意见)

页面开发选择 webpack+vue 生态静态页面    


模块 datasource 多数据源的阿里数据池
example 提供 3个样例
@Autowired
private JdbcTemplate jdbcTemplate;

@Autowired
private DataSource dataSource;

@Autowired
private SqlUtil sqlUtil;

展示取数据源操作

