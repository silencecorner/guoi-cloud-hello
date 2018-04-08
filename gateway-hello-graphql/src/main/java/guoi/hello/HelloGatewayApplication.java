package guoi.hello;


import graphql.servlet.GraphQLServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
public class HelloGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloGatewayApplication.class, args);
    }

}
