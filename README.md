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

# How it works
## Step 1. open browser [http://localhost:9999/graphiql](http://localhost:9999/graphiql)	
## Step 2. query 
### hellos query
```graphql
{
  hellos(after: "cursor") {
    edges {
      cursor
      node {
        id
        message
        create_time
        update_time
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
          "cursor": "cursor",
          "node": {
            "id": "hellos/9dbea423-e85d-4f44-a26b-b271295adce8",
            "message": "Hello conan, Welcome to Guoi Micro$!",
            "create_time": "2018-04-05T04:54:01.001Z",
            "update_time": "2018-04-05T04:54:01.001Z"
          }
        },
        {
          "cursor": "cursor",
          "node": {
            "id": "hellos/250f4442-368e-42d3-a18d-8aa71ad1f935",
            "message": "Hello conan1, Welcome to Guoi Micro$!",
            "create_time": "2018-04-05T04:54:12.918Z",
            "update_time": "2018-04-05T04:54:12.918Z"
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
      message
      create_time
      update_time
    }
  }
}
```
- Query Variables
```graphql
{
  "input": {"name": "conan"}
}
```

### hello create result
```graphql
{
  "data": {
    "hello0Create": {
      "hello": {
        "id": "hellos/83ffb798-f44b-42ae-beca-036a1b486b96",
        "message": "Hello conan, Welcome to Guoi Micro$!",
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
