# sensitive-encryption-demo

Spring Boot + MyBatis Plus 敏感数据加密示例项目。

## 技术栈

- Spring Boot 3.2.0
- MyBatis Plus 3.5.7
- MySQL
- Knife4j (Swagger)
- AES 加密

## 项目结构

```
src/main/java/com/example/demo/
├── config/          # 配置类（MP分页、Knife4j）
├── handler/         # MyBatis TypeHandler（加密/解密核心）
├── entity/          # 实体类
├── mapper/          # Mapper 接口
├── service/         # Service 层
├── controller/      # REST 接口
└── util/            # 工具类（AES加解密）
src/main/resources/
├── mapper/          # XML Mapper
├── db/schema.sql    # 建表脚本
└── application.yml  # 应用配置
```

## 加密方案

- **AES/CBC/PKCS5Padding** 对称加密，固定 Key 和 IV
- 自定义 `EncryptTypeHandler` 继承 `BaseTypeHandler<String>`
- 入库自动加密，出库自动解密，业务层无感知

### 两种集成方式

| 方式 | 说明 | 示例 |
|------|------|------|
| 注解方式 | 实体类字段标注 `@TableField(typeHandler=...)` | `User` 模块 |
| XML方式 | resultMap 和 SQL 参数中指定 `typeHandler` | `Employee` 模块 |

## 快速开始

1. 执行 `src/main/resources/db/schema.sql` 初始化数据库和表
2. 修改 `application.yml` 中的数据库连接信息
3. 运行 `DemoApplication.main()`
4. 访问 `http://localhost:8080/doc.html` 使用 Knife4j 测试接口

### 测试接口

| 路径 | 方法 | 说明 |
|------|------|------|
| `/user` | POST | 创建用户（敏感字段自动加密） |
| `/user/{id}` | GET | 根据ID查询 |
| `/user/id-card` | GET | 根据证件号查询 |
| `/employee` | POST | 新增员工（敏感字段自动加密） |
| `/employee/{id}` | GET | 根据ID查询 |
| `/employee/id-card` | GET | 根据证件号查询 |
