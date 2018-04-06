package guoi.hello.graphql.types.hello.mutation;

import com.github.conanchen.guoi.graphql.types.common.UserError;
import guoi.hello.graphql.types.hello.Hello;

import java.util.ArrayList;
import java.util.List;

public class HelloCreatePayload {
    //  # The hello object.
    private Hello hello;//        hello: Hello

    //  # List of errors that occurred executing the mutation.
    private List<UserError> userErrors = new ArrayList<>(0);//    userErrors: [UserError!]!


    /**
     * static factory method for builder
     */
    public static Builder builder() {
        return new HelloCreatePayload.Builder();
    }

    /**
     * forces use of the Builder
     */
    private HelloCreatePayload() {
    }

    /**
     * more fluent Builder
     */
    public static class Builder {
        private HelloCreatePayload managedInstance = new HelloCreatePayload();


        public Builder() {
        }

        /**
         * fluent setters for hello
         *
         * @param hello
         * @return
         */
        public Builder hello(Hello hello) {
            managedInstance.hello = hello;
            return this;
        }

        /**
         * fluent setters for userError
         *
         * @param userError
         * @return
         */
        public Builder withItem(UserError userError) {
            managedInstance.userErrors.add(userError);
            return this;
        }


        /**
         * build
         *
         * @return
         */
        public HelloCreatePayload build() {
            return managedInstance;
        }


    }

}
