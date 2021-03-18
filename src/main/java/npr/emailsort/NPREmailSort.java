package npr.emailsort;

import lombok.extern.java.Log;
import npr.emailsort.exception.NPREmailSortException;
import npr.emailsort.exception.NoFileProvidedException;
import npr.emailsort.handler.FileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

@Log
public class NPREmailSort {
    public static void main(String[] args) {
        FileHandler handler = new FileHandler();

        System.out.println("In the program!");

        try {
            for (String filePath : args) {
                log.info(String.format("Passing file %s to FileHandler", filePath));
                handler.handleFile(filePath);
            }
        } catch (FileNotFoundException e) {
            log.warning("No file found");
            throw new NoFileProvidedException();
        } catch (IOException e) {
            log.warning("Caught IOException");
            throw new NPREmailSortException();
        } finally {
            log.info("Closing Program");
        }
    }
}
