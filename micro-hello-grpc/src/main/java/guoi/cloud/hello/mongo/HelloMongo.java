package guoi.cloud.hello.mongo;

import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
public class HelloMongo {


    @Id
    private String name;//    string name = 1;

    private String message;//    string message = 2;

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

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }
}