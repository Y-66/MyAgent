



- # 特点

  - SpringAI技术栈
  - 类ChatGPT页面对话
  - 数据持久化存储
  - 支持Agent使用function calling
  - 会话历史
  - RAG

- # spring-ai-portal   ：前端代码

  环境配置：

  - Node 18.19.0

  启动：

  1. 进入目录下，安装包：

     ```shell
     npm install
     ```

  2. 启动

     ```shell
     npm run dev
     ```

- # src ：后端代码

  环境配置：

  - Java ：JDK17

  - Maven ： 3.9.10
  - mysql ： 5.7

  代码配置：

  ```yaml
  openai:
    api-key: your-key
    
  vectorstore:
    pinecone:
      api-key: your-key
      index-name: your-index-name
      
  datasource:
  	driver-class-name: com.mysql.cj.jdbc.Driver
  	url: jdbc:mysql://{{localhost:3306}}/{{your-database-name}}?serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&tinyInt1isBit=false&allowPublicKeyRetrieval=true&allowMultiQueries=true&useServerPrepStmts=false
  	username: root
  	password: 123456
  ```

  启动：

  启动Springboot。

  

