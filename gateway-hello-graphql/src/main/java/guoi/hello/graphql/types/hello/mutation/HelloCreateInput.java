package guoi.hello.graphql.types.hello.mutation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloCreateInput {
    //    #Hello Person's first name
    private String firstName;//    firstName: String!

    //            #Hello Person's last name
    private String lastName;//    lastName: String!
}
