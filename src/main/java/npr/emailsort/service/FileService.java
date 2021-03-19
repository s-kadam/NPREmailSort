package npr.emailsort.service;

import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import npr.emailsort.exception.FileWriteException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Log
@NoArgsConstructor
public class FileService {

    /**
     * Returns a list of all the emails in the file, omitting all blank lines. This method employs the
     * String.trim() method to ensure that any blank lines with spaces as characters to not get added to the
     * return list.
     * @param file original text file in question
     * @return a list of the emails in the text file
     * @throws IOException
     */
    public List<String> putOriginalEmailsIntoList(File file) throws IOException {
        final FileReader fileReader = new FileReader(file);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        final List<String> emailAddresses = new ArrayList<>();
        String email;

        log.info(String.format("Initialized BufferedReader to read file %s", file.getName()));
        while ((email = bufferedReader.readLine()) != null){
            if (email.trim().length() > 0) {
                log.info(String.format("Adding email %s to List of unvalidated and unsorted emails", email));
                emailAddresses.add(email);
            }
        }

        return emailAddresses;
    }

    /**
     * Writes the sorted and validated list of emails to a text file using PrintWriter.
     * @param sortedEmailList list of sorted and validated emails.
     * @param iteration integer indicating the argument which we are processing.
     *                  This is used to ensure output files are not overwritten
     *                  if more than one text file is passed as arguemnts to the program.
     * @throws FileWriteException
     * @throws FileNotFoundException
     */
    public void writeOutputTextFile(List<String> sortedEmailList, int iteration) throws FileWriteException, FileNotFoundException {
        PrintWriter writer = new PrintWriter(String.format("output%d.txt", iteration));

        if (sortedEmailList.size() > 0) {
            sortedEmailList.forEach(emailAddress -> {
                log.info("FileWriter is writing emails to file");
                writer.write(emailAddress + System.lineSeparator());
            });
        } else {
            log.info("No emails found in email List.");
            throw new FileWriteException();
        }

        writer.close();
    }
}
