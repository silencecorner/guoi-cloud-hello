# Introduction
Sample Hello Application demostrate following modules:
- gateway-hello-graphql
    > implements graphql query and mutation for Hello by calling grpc apis provided by 
    micro-hello-grpc
- micro-hello-grpc
    > implements grpc micro service for Hello CRUD 

## API Design Guide
- Refer to [Google API Design Guide](https://cloud.google.com/apis/design/) 
- Refer to  [Google real life example servicemanager.proto](https://github.com/googleapis/googleapis/blob/master/google/api/servicemanagement/v1/servicemanager.proto)
- And [local copy of Google real life example servicemanager.proto](apiexample.txt)


# How to use guo-micro-apis in your JAVA application 
## Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
	
## Step 2. Find the dependencies
[https://jitpack.io/com/github/conanchen/guoi-micro-apis/master-SNAPSHOT/build.log](https://jitpack.io/com/github/conanchen/guoi-micro-apis/master-SNAPSHOT/build.log)
```angular2html
Build artifacts:
...
com.github.conanchen.guoi-micro-apis:hello-grpc-java:master-5efdd7e810-1
...
```
## Step 3. Add Dependencies Cache Configuration To Your Main-module build.gradle
```gradle
configurations.all {
    //每隔10分钟..
    resolutionStrategy.cacheChangingModulesFor 10, 'minutes'
}
```
## Step 4. Add Dependencies As Needed To Your Sub-module build.gradle
```gradle
dependencies {
    compile ("com.github.conanchen.guoi-micro-apis:authcow-grpc-java:master-SNAPSHOT"){ changing = true }
    compile ("com.github.conanchen.guoi-micro-apis:gis-asset-grpc-java:master-SNAPSHOT"){ changing = true }
}
```
## Step 5. Refresh Dependencies & Skip Test
```bash

export http_proxy=http://127.0.0.1:1087;export https_proxy=http://127.0.0.1:1087;

gradle build --refresh-dependencies -x test
```

# How it works
## Step 1. open browser [http://localhost:9000/graphiql](http://localhost:9000/graphiql)	
## Step 2. query 
### hellos query
```graphql
{
  hellos(first: 3) {
    edges {
      cursor
      node {
        id
        lastName
        firstName
        message
      }
    }
  }
}
```
### hellos result
```graphql
{
  "data": {
    "hellos": {
      "edges": [
        {
          "cursor": "aGVsbG9zLzk0ODFhODJjLWJiNDMtNGMzZi04OTE2LTUwMmM5NTFkMDdjZg==",
          "node": {
            "id": "hellos/9481a82c-bb43-4c3f-8916-502c951d07cf",
            "lastName": "unknown",
            "firstName": "unknown",
            "message": "Hello conan, Welcome to Guoi Micro$!"
          }
        },
        {
          "cursor": "aGVsbG9zLzlmZjU3NTlhLTc3YmItNDJkYi05NzJiLWFhOTg4NTdmNTdhMQ==",
          "node": {
            "id": "hellos/9ff5759a-77bb-42db-972b-aa98857f57a1",
            "lastName": "unknown",
            "firstName": "unknown",
            "message": "Hello lihai, Welcome to Guoi Micro$!"
          }
        }
      ]
    }
  }
}          
```
## Step 3. Mutation
### hello create 
- mutation
```graphql
mutation ($input:HelloCreateInput!){
  hello0Create(input:$input){
    hello{
      id
      firstName
      lastName
      message
    }
  }
}
```
- Query Variables
```graphql
{
  "input": {
    "firstName":  "conan",
    "lastName":  "chen"
  }
}
```

### hello create result
```graphql
{
  "data": {
    "hello0Create": {
      "hello": {
        "id": "hellos/83ffb798-f44b-42ae-beca-036a1b486b96",
        "message": "Hello chen conan, Welcome to Guoi Micro$!",
        "create_time": "2018-04-06T07:36:10.935Z",
        "update_time": "2018-04-06T07:36:10.935Z"
      }
    }
  }
}
```

# Run Test Cases
On Windows if you encounter bellow errors:
```html
Caused by: java.io.IOException: Could not open inputStream for https://downloads.mongodb.org/win32/mongodb-win32-x86_64-3.2.2.zip
	at de.flapdoodle.embed.process.store.Downloader.downloadInputStream(Downloader.java:131)
	at de.flapdoodle.embed.process.store.Downloader.download(Downloader.java:69)
	at de.flapdoodle.embed.process.store.ArtifactStore.checkDistribution(ArtifactStore.java:66)
	at de.flapdoodle.embed.process.store.ExtractedArtifactStore.checkDistribution(ExtractedArtifactStore.java:60)
```
> - Download [https://downloads.mongodb.org/win32/mongodb-win32-x86_64-3.2.2.zip](https://downloads.mongodb.org/win32/mongodb-win32-x86_64-3.2.2.zip)
> - refer to [I made a folder in the directory ${user.home}/.embedmongo/win32/ and it worked!](https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo/issues/89)

# Building ahead of time for guoi-cloud-hello 
You can also build snapshots on each commit if you add GitHub Webhooks.

To add, head to repository Settings -> Webhooks & Services -> Add webhook.

Webhook URL: https://jitpack.io/api/webhooks

Content type: application/json

The webhook will trigger a build for branches that you have previously used with JitPack. So make sure you have requested master-SNAPSHOT from JitPack before adding a webhook.


# Pagination
## [Paginating Real-Time Data with Cursor Based Pagination](https://www.sitepoint.com/paginating-real-time-data-cursor-based-pagination/)
## [Pagination: You're (Probably) Doing It Wrong.](https://coderwall.com/p/lkcaag/pagination-you-re-probably-doing-it-wrong)
# istio 部署
- gateway-hello-graphql
    > 在网关中直接使用grpc的service name访问：service-hello.beidougx.com
    ##### 网关设置
  ```
  # 微服务地址，访问端口为6565
  helloServiceHost = service-hello.beidougx.com
  # 本网关的端口
  server.port=6565
  ```
- micro-hello-grpc
    >通过mongodb:27017 访问数据库，MongoDB的也是通过istio转发的
    ##### 微服务设置
    ```
    # grpc端口（http2)
    grpc.port=6565
    # tomcat端口,可访问监控metric数据（http1.1)    
    server.port=6566
    # mongodb的访问地址，通过istio转发
    spring.data.mongodb.host=mongodb
    # mongodb的端口
    spring.data.mongodb.port=27017
    # 数据库名称
    spring.data.mongodb.database=hello-db
    
    ```
