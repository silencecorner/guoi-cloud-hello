package guoi.hello.graphql.types.hello.mutation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HelloCreateInput {
    //    #Hello name
    private   String name;//    name: String!

}
