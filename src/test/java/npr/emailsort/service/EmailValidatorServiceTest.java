package npr.emailsort.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorServiceTest {
    private final ValidatorService validatorService = new ValidatorService();

    @Test
    void ensure_validateFileType_returns_true_when_reading_txt_file() {
        final String testFile1 = "test.txt";
        final String testFile2 = "test.img";
        final String testFile3 = "";
        final String testFile4 = "test4File1239728341234.txt";
        final String testFile5 = "txt.tst";

        assertTrue(validatorService.validateFileType(testFile1));
        assertTrue(validatorService.validateFileType(testFile4));
        assertFalse(validatorService.validateFileType(testFile2));
        assertFalse(validatorService.validateFileType(testFile3));
        assertFalse(validatorService.validateFileType(testFile5));
    }

    @Test
    void ensure_validateEmails_returns_only_valid_emails() {
        List<String> testEmails = new ArrayList<>();
        testEmails.add("david@müller.de");
        testEmails.add("margie@gmail.com");
        testEmails.add("john@company.net");
        testEmails.add("mack@de.dz");
        testEmails.add("virgil@demo.io");
        testEmails.add("jacob@my.me");
        testEmails.add("cole@gmail.com");
        testEmails.add("sam@");
        testEmails.add(" ");
        testEmails.add("just\"not\"right@example.com");
        testEmails.add("a\"b(c)d,e:f;g<h>i[j\\k]l@example.com");
        testEmails.add("no sir\"not\\allowed@example.com");
        testEmails.add("A@b@c@example.com");
        testEmails.add("cars.manson@googoo..com");
        testEmails.add("abc.example.com");
        testEmails.add("james@james");
        testEmails.add("..@gmail.com");
        testEmails.add("shiner@fullframe.com");

        List<String> expectedEmailList = new ArrayList<>();
        expectedEmailList.add("david@müller.de");
        expectedEmailList.add("margie@gmail.com");
        expectedEmailList.add("john@company.net");
        expectedEmailList.add("mack@de.dz");
        expectedEmailList.add("virgil@demo.io");
        expectedEmailList.add("jacob@my.me");
        expectedEmailList.add("cole@gmail.com");
        expectedEmailList.add("shiner@fullframe.com");

        testEmails = validatorService.validateEmails(testEmails);

        assertEquals(expectedEmailList, testEmails);
    }
}
