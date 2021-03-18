package npr.emailsort.handler;

import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import npr.emailsort.exception.UnsupportedFileTypeException;
import npr.emailsort.service.FileWriterService;
import npr.emailsort.service.SortService;
import npr.emailsort.service.ValidatorService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Log
@NoArgsConstructor
public class FileHandler {
    private final FileWriterService fileWriterService = new FileWriterService();
    private final SortService sortService = new SortService();
    private final ValidatorService validatorService = new ValidatorService();

    /**
     * The File Handler has a singular method handleFile.
     * This method is responsible for calling services to perform various tasks on the file.
     * Only calling methods and transforming data will happen here.
     * @param filePath
     */
    public void handleFile(String filePath) throws IOException {
        final File file = new File(filePath);
        final FileReader fileReader = new FileReader(file);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> emailAddresses = new ArrayList<>();
        ;

        if (!validatorService.validateFileType(file.getName())){
            log.info(file.getName());
            log.warning("Throwing UnsupportedFileTypeExtension. Only .txt files will be accepted");
            throw new UnsupportedFileTypeException();
        }

        String email;
        while ((email = bufferedReader.readLine()) != null){
            if (email.trim().length() > 0) {
                emailAddresses.add(email);
            }
        }

        emailAddresses = validatorService.validateEmails(emailAddresses);
        emailAddresses = sortService.sortEmailsIntoList(emailAddresses);

        fileWriterService.writeOutputTextFile(emailAddresses);
    }
}
