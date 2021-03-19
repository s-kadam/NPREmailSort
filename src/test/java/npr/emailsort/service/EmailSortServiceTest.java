package npr.emailsort.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class EmailSortServiceTest {
    private final SortService sortService = new SortService();

    @Test
    void ensure_sort_returns_list_sorted_by_domain(){
        List<String> testEmails = new ArrayList<>();
        testEmails.add("david@müller.de");
        testEmails.add("margie@gmail.com");
        testEmails.add("john@company.net");
        testEmails.add("mack@de.dz");
        testEmails.add("virgil@demo.io");
        testEmails.add("jacob@my.me");
        testEmails.add("cole@gmail.com");

        List<String> expectedEmailList = new ArrayList<>();
        expectedEmailList.add("john@company.net");
        expectedEmailList.add("mack@de.dz");
        expectedEmailList.add("virgil@demo.io");
        expectedEmailList.add("cole@gmail.com");
        expectedEmailList.add("margie@gmail.com");
        expectedEmailList.add("jacob@my.me");
        // 'ü' comes after 'y' in ASCII, therefore 'müller.de' comes after 'my.me'
        expectedEmailList.add("david@müller.de");

        testEmails = sortService.sortEmailsIntoList(testEmails);

        assertEquals(expectedEmailList, testEmails);
    }
}
