package npr.emailsort.handler;

import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import npr.emailsort.exception.UnsupportedFileTypeException;
import npr.emailsort.service.FileService;
import npr.emailsort.service.SortService;
import npr.emailsort.service.ValidatorService;

import java.io.*;
import java.util.List;

@Log
@NoArgsConstructor
public class FileHandler {
    private final FileService fileService = new FileService();
    private final SortService sortService = new SortService();
    private final ValidatorService validatorService = new ValidatorService();

    /**
     * The File Handler has a singular method handleFile.
     * This method is responsible for calling services to perform various tasks on the file.
     * Only calling methods and transforming data will happen here.
     * @param filePath path to txt file
     * @param iteration an integer to track the different number of outputs if there is more than one argument.
     * @throws IOException
     */
    public void handleFile(String filePath, int iteration) throws IOException {
        final File file = new File(filePath);
        List<String> emailAddresses;

        log.info(String.format("Passing %s to ValidatorService to validate .txt file extension", file.getName()));
        if (!validatorService.validateFileType(file.getName())){
            log.warning(file.getName() + " not validated as .txt file");
            log.warning("Throwing UnsupportedFileTypeExtension. Only .txt files will be accepted");
            throw new UnsupportedFileTypeException();
        }

        log.info(String.format("Passing %s to FileService to putOriginalEmailsIntoList", file.getName()));
        emailAddresses = fileService.putOriginalEmailsIntoList(file);

        log.info(String.format("Passing list of emails to ValidatorService to validateEmails", file.getName()));
        emailAddresses = validatorService.validateEmails(emailAddresses);

        log.info(String.format("Passing unsorted list of emails to SortService to sortEmailsIntoList", file.getName()));
        emailAddresses = sortService.sortEmailsIntoList(emailAddresses);

        log.info("Writing output file with list of sorted emails");
        fileService.writeOutputTextFile(emailAddresses, iteration);
    }
}
