package guoi.hello.graphql.types.hello;

//types HelloEdge {
//        # A cursor for use in pagination.
//        cursor: String!
//
//        # The item at the end of HelloEdge.
//        node: Hello!
//}
public class HelloEdge {
    //# A cursor for use in pagination.
    private final String cursor;//        cursor: String!
    //# The item at the end of HelloEdge.
    private final Hello node;//        node: Hello!

    public HelloEdge(String cursor, Hello node) {
        this.cursor = cursor;
        this.node = node;
    }
}
