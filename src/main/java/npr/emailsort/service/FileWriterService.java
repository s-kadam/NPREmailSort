package npr.emailsort.service;

import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import npr.emailsort.exception.FileWriteException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Log
@NoArgsConstructor
public class FileWriterService {
    public void writeOutputTextFile(List<String> sortedEmailList) throws IOException {
        FileWriter writer = new FileWriter("output/output.txt");
        sortedEmailList.forEach(emailAddress -> {
            try {
                writer.write(emailAddress + System.lineSeparator());
            } catch (IOException e) {
                log.warning("Problem writing emails to file");
                throw new FileWriteException();
            }
        });
        writer.close();
    }
}
