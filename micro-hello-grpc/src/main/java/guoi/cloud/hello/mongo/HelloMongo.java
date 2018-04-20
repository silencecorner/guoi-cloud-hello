package guoi.cloud.hello.mongo;

import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
public class HelloMongo {


    @Id
    private String  name;//    string  name = 1;

    private String first_name;//    string frist_name = 1;
    private String last_name;//    string frist_name = 2;

    private String message;//    string message = 3;

    private Date create_time;//    google.protobuf.Timestamp create_time = 13;
    private Date update_time;//    google.protobuf.Timestamp update_time = 14;


    /**
     * static factory method for builder
     */
    public static Builder builder() {
        return new HelloMongo.Builder();
    }

    /**
     * forces use of the Builder
     */
    private HelloMongo() {
    }

    /**
     * more fluent Builder
     */
    public static class Builder {
        private HelloMongo managedInstance = new HelloMongo();


        public Builder() {
        }


        /**
         * fluent setters for name
         *
         * @param name
         * @return Builder
         */
        public Builder name(String name) {
            managedInstance.name = name;
            return this;
        }

        /**
         * fluent setters for first_name
         *
         * @param first_name
         * @return Builder
         */
        public Builder firstName(String first_name) {
            managedInstance.first_name = first_name;
            return this;
        }

        /**
         * fluent setters for last_name
         *
         * @param last_name
         * @return Builder
         */
        public Builder lastName(String last_name) {
            managedInstance.last_name = last_name;
            return this;
        }

        /**
         * fluent setters for title
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
         * @return
         */
        public HelloMongo build() {
            return managedInstance;
        }


    }
}