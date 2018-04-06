package guoi.hello.graphql.types.hello;

import com.github.conanchen.guoi.graphql.types.common.PageInfo;

import java.util.List;

//types HelloConnection {
//        # A list of edges.
//        edges: [HelloEdge!]!
//
//        # Information to aid in pagination.
//        pageInfo: PageInfo!
//}
public class HelloConnection {
    //        # A list of edges.
    private final List<HelloEdge> edges;//        edges: [HelloEdge!]!
    //        # Information to aid in pagination.
    private final PageInfo pageInfo;//        pageInfo: PageInfo!

    public HelloConnection(List<HelloEdge> edges, PageInfo pageInfo) {
        this.edges = edges;
        this.pageInfo = pageInfo;
    }
}
