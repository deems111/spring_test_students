package configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import testConfig.TestConfig;

import java.util.Locale;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = LanguageConfigTest.class)
@Import({TestConfig.class})
public class LanguageConfigTest {

    @Autowired
    private LanguageConfig languageConfig;

    @Test
    public void localeTest() {
        //default locale
        assertEquals(Locale.ENGLISH, languageConfig.getLocale());
        languageConfig.setLocale(new Locale("ru"));
        assertEquals("ru", languageConfig.getLocale().toString());
    }

}