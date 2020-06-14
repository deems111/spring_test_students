import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import service.TestImpl;

@SpringBootApplication
@ComponentScan(basePackages = {"configuration", "controller", "dao", "dto", "service"})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args).getBean(TestImpl.class).test(System.out, System.in);
    }
}
