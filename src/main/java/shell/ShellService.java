package shell;

import configuration.LanguageConfig;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import service.TestImpl;

@ShellComponent
@AllArgsConstructor
public class ShellService {

    private final TestImpl testService;
    private final LanguageConfig languageConfig;


    @ShellMethod(value = "Start Student's Test", key = "test")
    public void test() {
        testService.test(System.out, System.in);
    }

}
