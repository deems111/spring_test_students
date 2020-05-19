package testConfig;

import configuration.MessageSourceConfig;
import controller.StudentTestControllerImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;

@Profile("test")
@PropertySource("classpath:test.properties")
@ComponentScan(basePackages = {"controller", "dao", "dto", "service"})
@Import({MessageSourceConfig.class})
public class TestConfig {

    @Bean
    StudentTestControllerImpl controller() {
        return Mockito.mock(StudentTestControllerImpl.class);
    }

}
