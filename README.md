# xcAlibaba
                                                     开发规范
#
- Feign放在client 包，无前缀后缀为Client
- 只在服务内部调用的Controller放在controller包类前缀为Inner后缀为Controller
- 对外提供的接口放在controller/api包中后缀为Controller
- 如果是服务内部和外部都有用的接口也放到controller/api中
- 接口有修改的话，在提交联调时修改项目中的CHANGELOG.md文件
- 依赖注入一律使用@Resource
- 一个功能有两个接口实现，则将老的接口标记为@Deprecated
- 建表时必须字段 id、gmt_create、gmt_update、is_delete(tinyint(1)),
- 自定义header 前缀为X
- 使用GetMapping\PostMapping\PatchMapping
- userId表示当前登录用户
- xxxUtils xxxHelper
- 数据校验利用valid实现
- GET\SET 不写任何业务逻辑
- 代码不超过120字 Editor/Code Style
- SQL写在/resources/data/目录下
- 时间段字段名一律使用 startDate/endDate
- url禁止以 / 结尾
- 接口版本使用请求头`X-API-Version`控制版本
- 常用code
- 200 响应成功
- 400 1. 请求错误 2. 参数有误
- 401 未进行授权或授权校验失败
- 403 授权校验通过，但无权限访问
- 500 服务器错误
请求及响应

## 请求
### 1.1 Header
1. 自定义请求头，前缀必须为 **X**,例如`X-Access-Token`
2. 所有请求必传请求头
- X-Access-Token（如已登录则必传，否则不传）,返回值为登录接口返回的token
- X-API-Version=104 API接口版本
- X-App-Version=iOS:100 App系统及App版本(Android:100)
- X-App-Channel=AppStore App下载渠道
- X-Device-Info={设备型号}:{系统版本}:{device_id}:{system_id} system_id为仅Android系统存在，iOS系统device_id\system_id值传相同值
- X-Net-Info=WIFI:192.168.123.75
- Date=1536213870000 请求发起时系统时间(由于取的是设备时间，并不准确，目前已弃用)

### 1.2 Param
1. 当参数名为`user_id`时，只能表示当前登录用户
2. **时间段**字段名一律使用 `startDate`、`endDate`
## 响应
### 1.1 错误码
- 200 响应成功
- 400 1. 请求错误 2. 参数有误
- 401 未进行授权或授权校验失败
- 403 授权校验通过，但无权限访问
- 500 服务器错误

### 1.1 错误信息
todo
# Redis
> 参考<br>
> [Redis key naming conventions?](https://stackoverflow.com/questions/6965451/redis-key-naming-conventions)<br>
> [阿里云Redis开发规范](https://yq.aliyun.com/articles/531067)<br>
> [Redis 内存考虑](https://www.w3cschool.cn/redis_all_about/redis_all_about-s2kd26wc.html)<br>

## 键值设计
只要有可能的话，就尽量使用hash而不是string来储存键值对数据，因为散列键管理方便、能够避免键名冲突。

## 1.1 Key
1. 有Redis Key都需存入news_manage.news_redis_data_dictionary
2. [建议]以模块名/业务名或库名为前缀,用冒号分割 业务名:表名:id
```
wemedia:user:{user_id}
bd:user:{user_id}
```
3. [强制]多个单词以点号做分割，对于英文字母一律使用小写
```
user.info:{user_id}
```
4. [建议]在保证语义情况下尽量缩短key的长度,推荐对常用的单词规定缩写,例如
user:{uid}:friends:messages:{mid}
//简化为
u:{uid}:fr:m:{mid}
```
5. **示例**
```
// https://api.github.com/users/{user}/repos{?type,page,per_page,sort}
// github:user:xudaojie:repo:{repo_name}

// 获取自媒体今天获取的积分
// mediaintegral:{user_id} string
// 模块:业务/表名:{变量}
wemedia:user.integral:{user_id}

// 牛评
// commentHot:{data_id} hash
// comment:hot.comment:data.id:{data_id}
comment:hot.comment:content:{data_id}

// 新闻喜欢级别
// newsGrade:{data_id} hash
comment:content.like.grade:{data_id}
```

// 用户申请提现队列
// withdraw_cash_info list
// manage:withdraw.cash:queue

// 活跃自媒体用户
// USER_ID_LIST_KEY: set
// wemedia:hot.user

## 1.2 Value

# MySQL

## 必须字段
- id bigint(11) 主键id、自增、unsigned
- gmt_create datetime(0) 插入时间
- gmt_update datetime(0) 更新时间

## 字段命名
1. 布尔类型字段名前缀为**is**，字段类型使用(tinyint(1))，例如is_delete(tinyint(1))
2. 状态、类型字段命名 status(tinyint(3)) / type(tinyint(3))

##
1. varchar 类型默认值为 ''
2. 数值类型 默认值为 0
3. 金额相关字段单位为分，数据类型使用bigint(11)
### 6.2 工具类
工具类使用后缀为Utils,如果此工具类需要实例化则以Helper为后缀。

### 6.3 Controller
- 只在服务内部调用的Controller放在controller包类前缀为Inner后缀为Controller
- 对外提供的接口放在controller/api包中后缀为Controller
- 如果是服务内部和外部都有用的接口也放到controller/api中
- 使用@GetMapping\@PostMapping\@PatchMapping\@PutMapping\@DeleteMapping
- url禁止以 `/` 结尾,例如`http://api.test.com/api/comment/`,应使用`http://api.test.com/api/comment`

### 6.4 其他
- 依赖注入一律使用@Resource
- 一个功能有两个接口实现，则将老的接口标记为`@Deprecated`
- get\set方法中不写任何业务逻辑
- mapper.xml文件写在`/resources/data/`目录下
### sonarLint代码扫描




