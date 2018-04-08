package guoi.hello.graphql.types.hello.mutation;

import lombok.Data;

@Data
public class HelloCreateInput {
    //    #Hello name
    private   String name;//    name: String!

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HelloCreateInput(String name) {
        this.name = name;
    }
}
