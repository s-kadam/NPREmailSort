package npr.emailsort.service;

import java.util.Comparator;
import java.util.List;

public class SortService {
    private static final String AT_SIGN = "@";

    public List<String> sortEmailsIntoList(List<String> unsortedList) {
        unsortedList.sort(Comparator
                .comparing(email -> email.substring(unsortedList.lastIndexOf(AT_SIGN) + 1)));

        return unsortedList;
    }
}
