package npr.emailsort.service;

import lombok.extern.java.Log;
import npr.emailsort.exception.UnsupportedFileTypeException;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log
public class ValidatorService {
    private final String PERIOD = ".";
    private         final String TEXT_EXTENSION = "txt";


    /**
     * Returns a list of validated emails.
     * @param emails
     * @return
     */
    public List<String> validateEmails(List<String> emails){
        final EmailValidator validator = EmailValidator.getInstance();
        return emails.stream()
            .filter(validator::isValid)
            .collect(Collectors.toList());
    }

    /**
     * Returns a boolean variable true if the file passed as param is
     * has the extension ".txt". This method does not validate the contents
     * of the file to be true text file
     * @param fileName name of or path to file
     * @return true if file is a .txt file, false otherwise
     * @throws UnsupportedFileTypeException
     */
    public boolean validateFileType(String fileName) throws UnsupportedFileTypeException {
        if (getFileExtension(fileName).equals(TEXT_EXTENSION)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the file type in the form of an Optional String object, if the param exists.
     * If the param does not exist, it will return an empty string.
     * @param fileName the name of the file
     * @return the file extension without the leading period
     */
    private Optional<String> getFileExtension(String fileName) {
        return Optional.ofNullable(fileName)
                .filter(name -> name.contains(PERIOD))
                .map(name -> name.substring(fileName.lastIndexOf(PERIOD) + 1));
    }
}
