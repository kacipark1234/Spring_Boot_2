# Spring_Boot_2

這是一個使用Spring Boot搭建的會員登錄系統練習，採用MVC框架進行設計和實現。該系統提供用戶註冊、登錄和註銷功能，並且只允許已註冊的用戶訪問系統的特定功能。

# 功能
用戶註冊：用戶可以通過提供用戶名、密碼和電子郵件地址進行註冊  
用戶登錄：已註冊的用戶可以使用其帳密登錄系統  
用戶刪除：用戶可以從系統中刪除帳號(軟刪除)  

# 技術
Java  
Spring Boot  
Spring MVC  
Thymeleaf（作為模板引擎）  
HTML/CSS（用於前端界面）  
MySQL（用於存儲用戶數據）  

# 使用環境
Eclipse  

# 資料庫配置

```
spring.datasource.url=jdbc:mysql://localhost:3306/your-database-name
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

# 畫面顯示

## 登入畫面
![image](https://github.com/kacipark1234/Spring_Boot_2/assets/93324400/f84b21ea-1c7f-459b-9f0e-32773dda24bd)

## 新增帳號
![image](https://github.com/kacipark1234/Spring_Boot_2/assets/93324400/95633fdb-ace7-4c4d-b71d-5816d2aa3ca5)

## 顯示所有帳號資料
![image](https://github.com/kacipark1234/Spring_Boot_2/assets/93324400/c9b03c6f-5a58-4756-a519-9ca3448f456d)

## 成功登入畫面
![image](https://github.com/kacipark1234/Spring_Boot_2/assets/93324400/3a7ffc9a-151e-4084-9418-d172def32db2)

### 編輯畫面
![image](https://github.com/kacipark1234/Spring_Boot_2/assets/93324400/df78cad0-6722-4e39-9874-73b86e93cabe)

