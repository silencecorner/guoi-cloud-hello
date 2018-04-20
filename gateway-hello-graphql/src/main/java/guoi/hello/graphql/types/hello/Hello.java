package guoi.hello.graphql.types.hello;

import lombok.Getter;

import javax.annotation.concurrent.Immutable;
import java.util.Date;


/**
 * # A hello represents a hello message.
 * type Hello implements Node {
 * # Globally unique identifier.
 * id: ID!
 * <p>
 * # The hello message .
 * message: String!
 * <p>
 * # hello creation date time
 * create_time: DateTime!
 * <p>
 * # hello update date time
 * update_time: DateTime!
 * }
 */
@Getter
@Immutable
public class Hello {
    //  # Globally unique identifier.
    private String id;// id: ID!

    //    #Hello Person's first name
    private String first_name;//    first_name: String!
    //
    //            #Hello Person's last name
    private String last_name;//    last_name: String!

    // # The hello message
    private String message;// message: String!

    private Date create_time; // catalog entity creation date time create_time: DateTime!
    private Date update_time; // catalog entity update date time update_time: DateTime!

    //  # The customer’s default address.
//    private Address defaultAddress;//            defaultAddress: Address

//    private Image image;


    /**
     * static factory method for builder
     */
    public static Builder builder() {
        return new Hello.Builder();
    }

    /**
     * forces use of the Builder
     */
    private Hello() {
    }

    /**
     * more fluent Builder
     */
    public static class Builder {
        private Hello managedInstance = new Hello();


        public Builder() {
        }


        /**
         * fluent setters for id
         *
         * @param id
         * @return Builder
         */
        public Builder id(String id) {
            managedInstance.id = id;
            return this;
        }

        /**
         * fluent setters for first_name
         *
         * @param first_name
         * @return
         */
        public Builder firstName(String first_name) {
            managedInstance.first_name = first_name;
            return this;
        }

        /**
         * fluent setters for last_name
         *
         * @param last_name
         * @return
         */
        public Builder lastName(String last_name) {
            managedInstance.last_name = last_name;
            return this;
        }

        /**
         * fluent setters for message
         *
         * @param message
         * @return
         */
        public Builder message(String message) {
            managedInstance.message = message;
            return this;
        }

        /**
         * fluent setters for creatAt Datetime
         *
         * @param date
         * @return
         */
        public Builder createdAt(Date date) {
            managedInstance.create_time = date;
            return this;
        }

        /**
         * fluent setters for creatAt Datetime
         *
         * @param date
         * @return
         */
        public Builder updateAt(Date date) {
            managedInstance.update_time = date;
            return this;
        }


        /**
         * build
         *
         * @return
         */
        public Hello build() {
            return managedInstance;
        }


    }
}