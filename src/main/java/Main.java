import configuration.MessageSourceConfig;
import configuration.ReaderConfig;
import org.springframework.context.annotation.*;
import service.TestImpl;

@PropertySource("classpath:app.properties")
@Configuration
@ComponentScan(basePackages = {"controller", "dao", "dto", "service"})
@Import({MessageSourceConfig.class, ReaderConfig.class})
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TestImpl test = context.getBean(TestImpl.class);
        test.test();
    }
}
