package testConfig;

import configuration.MessageSourceConfig;
import configuration.ReaderConfig;
import controller.StudentTestControllerImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;

@Profile("test")
@Configuration
@PropertySource("classpath:test.properties")
@ComponentScan(basePackages = {"controller", "dao", "dto", "service"})
@Import({MessageSourceConfig.class, ReaderConfig.class})
public class TestConfig {

    @Bean
    StudentTestControllerImpl controller() {
        return Mockito.mock(StudentTestControllerImpl.class);
    }

}
