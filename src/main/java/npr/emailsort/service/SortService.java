package npr.emailsort.service;

import lombok.extern.java.Log;

import java.util.Comparator;
import java.util.List;

@Log
public class SortService {
    private static final String AT_SIGN = "@";

    /**
     * Returns a sorted list of emails, sorted by domain first,
     * then sorted alphabetically amongst those domains. This method employs the use of
     * the sort and utility methods introduced to the List interface in Java 8. Comparator
     * no longer needs to be implemented with the introduction of the utility methods.
     * @param unsortedList unsorted List of emails
     * @return sorted list of emails
     */
    public List<String> sortEmailsIntoList(List<String> unsortedList) {
        log.info("Sorting list of emails");
        unsortedList.sort(Comparator
                .comparing((String email) -> email.substring(email.lastIndexOf(AT_SIGN) + 1))
                .thenComparing((String email) -> email.substring(0, email.lastIndexOf(AT_SIGN))));
        return unsortedList;
    }
}
