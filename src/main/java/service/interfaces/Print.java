package service.interfaces;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

public interface Print {

    void printLine(String line);

    void init(PrintStream out, Reader in);
}
