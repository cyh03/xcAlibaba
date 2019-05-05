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
本文档参考Google Java编程风格规范。
> [Google Java编程风格指南](http://www.hawstein.com/posts/google-java-style.html)

## 包结构
```yaml
service:
impl:
UserServiceImpl:
UserService:
controller:
api:
UserController:
InnerUserController:
data:
client:
mongo:
UserDocument: UserDataSource实现类
mapper:
UserDataSource: 接口
UserRepository: UserDataSource实现类
resource:
data:
mapper:
```

## 源文件
### 2.1 文件名
源文件以其最顶层的类名来命名，大小写敏感，文件扩展名为.java。
### 2.2 文件编码：UTF-8
源文件编码格式为 UTF-8。

## 源文件结构
一个源文件包含(按顺序地)：

- 许可证或版权信息(如有需要)
- package语句
- import语句
- 一个顶级类(只有一个)
- 以上每个部分之间用一个空行隔开。

## 3.1 类声明
### 3.4.1 类成员顺序
类的成员顺序对易学性有很大的影响，但这也不存在唯一的通用法则。不同的类对成员的排序可能是不同的。 最重要的一点，每个类应该以某种逻辑去排序它的成员，维护者应该要能解释这种排序逻辑。比如， 新的方法不能总是习惯性地添加到类的结尾，因为这样就是按时间顺序而非某种逻辑来排序的。

#### 3.4.2.1 重载：永不分离
当一个类有多个构造函数，或是多个同名方法，这些函数/方法应该按顺序出现在一起，中间不要放进其它函数/方法。
## 格式
### 4.1 列限制：100或120

*例外:*
- 不可能满足列限制的行(例如，Javadoc中的一个长URL)
- package和import语句
- 注释中那些可能被剪切并粘贴到shell中的命令行。

## 4.2 自动换行
术语说明：一般情况下，一行长代码为了避免超出列限制(80或100个字符)而被分为多行，我们称之为自动换行(line-wrapping)。

我们并没有全面，确定性的准则来决定在每一种情况下如何自动换行。很多时候，对于同一段代码会有好几种有效的自动换行方式。

> Tips<br>
> 提取方法或局部变量可以在不换行的情况下解决代码过长的问题(是合理缩短命名长度吧)

## 4.2.1 从哪里断开

1. 如果在`非赋值运算符`处断开，那么在该符号前断开(比如+，它将位于下一行)。注意：这条规则也适用于以下“类运算符”符号：点分隔符
## 4.3 空白
除了语言需求和其它规则，并且除了文字，注释和Javadoc用到单个空格，单个ASCII空格也出现在以下几个地方：

1. 分隔任何保留字与紧随其后的左括号(()(如if, for catch等)。
2. 分隔任何保留字与其前面的右大括号(})(如else, catch)。
3. 在任何左大括号前({)，两个例外：
- `@SomeAnnotation({a, b})`(不使用空格)。
- `String[][] x = foo;`(大括号间没有空格，见下面的Note)。
4. 在任何二元或三元运算符的两侧。这也适用于以下“类运算符”符号：
- 类型界限中的&(<T extends Foo & Bar>)。
- catch块中的管道符号(catch (FooException | BarException e)。
- foreach语句中的分号。
5. 在, : ;及右括号())后
6. 如果在一条语句后做注释，则双斜杠(//)两边都要空格。这里可以允许多个空格，但没有必要。
7. 类型和变量之间：List list。
8. 数组初始化中，大括号内的空格是可选的，即new int[] {5, 6}和new int[] { 5, 6 }都是可以的。
> Note：这个规则并不要求或禁止一行的开关或结尾需要额外的空格，只对内部空格做要求。
## 命名约定
### 5.1 标识符类型的规则

### 5.1.1 类名
类名都以UpperCamelCase风格编写。

类名通常是名词或名词短语，接口名称有时可能是形容词或形容词短语。现在还没有特定的规则或行之有效的约定来命名注解类型。

测试类的命名以它要测试的类的名称开始，以Test结束。例如，HashTest或HashIntegrationTest。

### 5.1.2 常量名
常量名命名模式为CONSTANT_CASE，全部字母大写，用下划线分隔单词。那，到底什么算是一个常量？

每个常量都是一个静态final字段，但不是所有静态final字段都是常量。在决定一个字段是否是一个常量时， 考虑它是否真的感觉像是一个常量。例如，如果任何一个该实例的观测状态是可变的，则它几乎肯定不会是一个常量。 只是永远不打算改变对象一般是不够的，它要真的一直不变才能将它示为常量。


