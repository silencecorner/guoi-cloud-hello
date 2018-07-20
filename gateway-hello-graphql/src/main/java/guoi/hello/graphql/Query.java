package guoi.hello.graphql;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.github.conanchen.guoi.cloud.hello.grpc.ListHellosResponse;
import com.github.conanchen.guoi.graphql.types.ZGuoiCommonTypes;
import com.github.conanchen.guoi.graphql.types.common.PageInfo;
import guoi.hello.graphql.types.hello.Hello;
import guoi.hello.graphql.types.hello.HelloConnection;
import guoi.hello.graphql.types.hello.HelloEdge;
import guoi.hello.graphql.types.hello.query.HelloFilterInput;
import guoi.hello.graphql.types.hello.query.HelloOrderByInput;
import guoi.hello.grpc.client.HelloGrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@EnableAutoConfiguration
public class Query implements GraphQLQueryResolver {

    private ZGuoiCommonTypes zGuoiCommonTypes;//    zGuoiCommonTypes: ZGuoiCommonTypes

    @Autowired
    HelloGrpcClient helloGrpcClient;

    public Query() {

    }


    public Future<HelloConnection> hellos(
            //# Returns the elements in the list that come after the specified global ID.
            String after,//after: String
            //  # Returns the elements in the list that come before the specified global ID.
            String before,//before: String
            //  # Returns the first _n_ elements from the list.
            Integer first,//first: Int
            //  # Returns the last _n_ elements from the list.
            Integer last,//last: Int
            //  # Skip the _n_ elements from the list.
            Integer skip,//skip: Int
            //# Filter condition
            HelloFilterInput filter,// filter: HelloFilterInput
            //# Order options for hellos return from the connection
            HelloOrderByInput orderBy//  orderBy: HelloOrderByInput
    ) {
        CompletableFuture<HelloConnection> completableFuture
                = new CompletableFuture<>();
        helloGrpcClient.getAsyncHellos(new HelloGrpcClient.HellosCallback() {
            @Override
            public void onHellosResponse(ListHellosResponse hellosResponse) {
                HelloConnection helloConnection = new HelloConnection(new ArrayList<HelloEdge>() {{
                    hellosResponse.getHelloList().forEach(hello -> add(
                            new HelloEdge(Base64Coder.encodeString(hello.getName()),
                                    Hello
                                            .builder()
                                            .id(hello.getName())
                                            .firstName(hello.getFirstName())
                                            .lastName(hello.getLastName())
                                            .message(hello.getMessage())
                                            .createdAt(new Date(hello.getCreateTime().getSeconds()))
                                            .updatedAt(new Date(hello.getUpdateTime().getSeconds()))
                                            .build())
                            )
                    );
                }}, PageInfo.builder().endCursor("end").startCursor("start").hasNextPage(Boolean.FALSE).hasPreviousPage(Boolean.FALSE).totalCount(1).build());
                completableFuture.complete(helloConnection);
            }
        });
        return completableFuture;
    }
//
//    public Future<HelloConnection> hellos(
////# Returns the elements in the list that come after the specified global ID.
//String after,//after: String
////
////  # Returns the elements in the list that come before the specified global ID.
//String before,//before: String
////
////  # Returns the first _n_ elements from the list.
//Integer first,//first: Int
////
////  # Returns the last _n_ elements from the list.
//Integer last//last: Int
////
////  # Ordering options for repositories returned from the connection
////  # orderBy: RepositoryOrder
//
//    ) {
//
//        CompletableFuture<HelloConnection> completableFuture
//    = new CompletableFuture<>();
//
//        Executors.newCachedThreadPool().submit(() -> {
//Thread.sleep(500);
//HelloConnection helloConnection = new HelloConnection(new ArrayList<HelloEdge>(){{
//    add(new HelloEdge("cursor1", com.bdgx.guoi.shopiebackend.graphql.types.hello.Hello
//.builder()
//.id("id1")
//.message("message1")
//.createdAt(new Date())
//.updateAt(new Date())
//.build()));
//    add(new HelloEdge("cursor2", com.bdgx.guoi.shopiebackend.graphql.types.hello.Hello
//.builder()
//.id("id2")
//.message("message2")
//.createdAt(new Date())
//.updateAt(new Date())
//.build()));
//    add(new HelloEdge("cursor3", com.bdgx.guoi.shopiebackend.graphql.types.hello.Hello
//.builder()
//.id("id3")
//.message("message3")
//.createdAt(new Date())
//.updateAt(new Date())
//.build()));
//}}, new PageInfo("end", Boolean.FALSE, Boolean.FALSE, "start"));
//completableFuture.complete(helloConnection);
//return null;
//        });
//
//        return completableFuture;
//
//    }
}