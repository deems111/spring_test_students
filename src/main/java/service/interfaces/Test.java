package service.interfaces;

import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Base interface for proceeding test
 */
public interface Test {

    void init(PrintStream out, InputStreamReader in);

    void test(PrintStream out, InputStreamReader in);
}
