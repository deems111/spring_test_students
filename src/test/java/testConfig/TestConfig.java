package testConfig;

import configuration.Config;
import controller.StudentTestControllerImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;

import java.io.FileNotFoundException;
import java.io.PrintStream;

@Profile("test")
@PropertySource("classpath:test.properties")
@ComponentScan(basePackages = {"controller", "dao", "dto", "service"})
@Import({Config.class})
public class TestConfig {

    @Bean
    StudentTestControllerImpl controller() {
        return Mockito.mock(StudentTestControllerImpl.class);
    }

    @Bean
    public PrintStream out() {
        try {
            return new PrintStream("C://Users//dmitr//test.txt");
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}
