# 使用Spring Boot实现OAuth2客户端
 
## 概述
本仓库共提供了三个应用：
- OAuth2Server/ – 用于测试的实现了OpenID Connect和OAuth 2.0 协议的安全令牌服务提供者应用
- spring-security-oauth2-api/ - 基于Spring Boot受OAuth2服务保护的API应用
- spring-security-oauth2-client/ - 用于登录和访问API的Spring MVC客户端应用


## 构建与运行前提条件

在当前机器上安装下列软件:
1. Java JDK 17
2. Apache Maven 4.0.0-alpha-8或更高版本
3. Node.js v20.1.0或更高版本
4. Angular CLI 16.0.1或更高版本
5. .NET Core 3.1 SDK

## 构建Spring Boot应用
在代码根目录下，进入API应用所在目录：
```
cd spring-security-oauth2-api
```

然后运行以下命令来构建API应用: 
```
mvn clean package
```
它将在应用下得/target目录产生对应的jar文件。

在代码根目录下，进入客户端应用所在目录：
```
cd spring-security-oauth2-client
```

然后运行以下命令来构建客户端应用: 
```
mvn clean package
```
它将在应用下得/target目录产生对应的jar文件。


## 如何运行

- 运行安全令牌服务提供者应用

在代码根目录下，进入安全令牌服务提供者应用所在目录：
```
cd OAuth2Server
```
然后运行以下命令来运行应用
```
dotnet run -f netcoreapp3.1
```

- 运行API应用

在上面构建出API应用的jar文件后，在代码根目录下，运行如下命令来运行API应用:
```
cd spring-security-oauth2-api\target
java -jar spring-security-oauth2-api-0.0.1-SNAPSHOT.jar
```

- 运行Spring MVC客户端应用

在上面构建出客户端应用的jar文件后，在代码根目录下，运行如下命令来运行API应用:
```
cd spring-security-oauth2-client\target
java -jar spring-security-oauth2-client-0.0.1-SNAPSHOT.jar
```

最后打开浏览器输入http://localhost:8081来测试应用。