package az.edu.ada.wm2.Assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

/**
 * The main entry point for the Assignment application. This class configures and starts the Spring Boot application.
 */
@SpringBootApplication
public class AssignmentApplication {

    /**
     * The main method to start the Assignment application. This method initializes and runs the Spring Boot application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(AssignmentApplication.class, args);
    }

}
