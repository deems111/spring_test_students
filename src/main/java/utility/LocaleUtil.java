package utility;

import java.util.Locale;
import java.util.StringTokenizer;

public class LocaleUtil {

    /**
     * Get java.util.locale from String.
     * FE - Convert ru_Ru to russian language
     */
    public static Locale getLocale(String localeStr) {

        StringTokenizer tokenizer = new StringTokenizer(localeStr, "_");
        String language = tokenizer.hasMoreTokens() ? (String) tokenizer.nextElement() : null;
        String country = tokenizer.hasMoreTokens() ? (String) tokenizer.nextElement() : null;
        String special = tokenizer.hasMoreTokens() ? (String) tokenizer.nextElement() : null;
        return new Locale(language, country, special);
    }

}
