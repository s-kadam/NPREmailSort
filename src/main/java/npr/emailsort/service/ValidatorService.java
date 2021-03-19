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
    private final String TEXT_EXTENSION = "txt";

    /**
     * Returns a list of validated emails. This method takes advantage of the
     * Apache Commons Email Validator, which complies to RFC 2822 standards.
     * It takes in a list of unvalidated emails, filters them with the Email Validator,
     * and returns a list of the validated emails.
     * REGEX could have been used, but that leaves a rather messy implementation, and most of the time,
     * most REGEX will not account for all emails. Unless there is a specific business standard provided
     * for the types of emails allowed, it is best to adhere to RFC standards.
     * @param emails list of emails which need to be validated.
     * @return List of validated emails.
     */
    public List<String> validateEmails(List<String> emails){
        final EmailValidator validator = EmailValidator.getInstance();
        return emails.stream()
            .filter(validator::isValid)
            .collect(Collectors.toList());
    }

    /**
     * Returns a boolean variable true if the file passed as param is
     * has the extension ".txt". This method does not validate contents of the file.
     * If a file has been tampered with to have the ".txt" extension, this method will
     * return true.
     * @param fileName name of or path to file
     * @return true if file is a .txt file, false otherwise
     * @throws UnsupportedFileTypeException
     */
    public boolean validateFileType(String fileName) throws UnsupportedFileTypeException {
        if (getFileExtension(fileName).isPresent() && getFileExtension(fileName).get().equals(TEXT_EXTENSION)) {
            log.info(String.format("%s validated as .txt file", fileName));
            return true;
        }
        log.info(String.format("%s is not a .txt file. Only .txt files will be accepted", fileName));
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
