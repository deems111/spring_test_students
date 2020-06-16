package testConfig;

import configuration.Config;
import configuration.LanguageConfig;
import controller.StudentTestControllerImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;

import java.io.FileNotFoundException;
import java.io.PrintStream;

@ComponentScan(basePackages = {"configuration", "controller", "dao", "dto", "service"})
@Import({Config.class, LanguageConfig.class})
public class TestConfig {

    @Bean
    StudentTestControllerImpl controller() {
        return Mockito.mock(StudentTestControllerImpl.class);
    }


}
