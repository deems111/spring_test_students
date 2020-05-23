import configuration.Config;
import org.springframework.context.annotation.*;
import service.TestImpl;

@PropertySource("classpath:app.properties")
@ComponentScan(basePackages = {"controller", "dao", "dto", "service"})
@Import({Config.class})
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TestImpl test = context.getBean(TestImpl.class);
        test.test();
    }
}
