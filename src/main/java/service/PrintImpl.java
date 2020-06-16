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

    private PrintStream out;
    private InputStream in;


    @Override
    public void printLine(String line) {
        out.println(line);
    }

}
