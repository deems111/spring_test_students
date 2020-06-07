package service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.interfaces.Print;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

@Data
@Service
public class PrintImpl implements Print {

    private final PrintStream out;
    private final InputStream in;

    @Autowired
    public PrintImpl(PrintStream out, InputStream in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void printLine(String line) {
        out.println(line);
    }

}
