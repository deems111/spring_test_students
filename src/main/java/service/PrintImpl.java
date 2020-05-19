package service;

import lombok.Data;
import org.springframework.stereotype.Service;
import service.interfaces.Print;

import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

@Data
@Service
public class PrintImpl implements Print {

    private PrintStream out;
    private Reader in;

    @Override
    public void printLine(String line) {
        out.println(line);
    }

    @Override
    public void init(PrintStream out, Reader in) {
        this.out = out;
        this.in = in;
    }
}
