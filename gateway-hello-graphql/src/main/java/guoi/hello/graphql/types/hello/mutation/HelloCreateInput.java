package guoi.hello.graphql.types.hello.mutation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloCreateInput {
    //    #Hello Person's first name
    private String first_name;//    first_name: String!

    //            #Hello Person's last name
    private String last_name;//    last_name: String!

}
