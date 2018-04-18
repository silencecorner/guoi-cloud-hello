package guoi.hello.graphql.types.hello.mutation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloCreateInput {
    //    #Hello name
    private   String name;//    name: String!

}
