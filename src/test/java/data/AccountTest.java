package data;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.util.ArrayList;
import java.util.List;

import static data.Account.SECURITY_STORAGE_FILE_PATH;
import static data.Account.expensesStorageFilePath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static common.AccountMessage.NO_SPECIAL_CHARACTERS_MESSAGE;
import static common.AccountMessage.PASSWORD_LENGTH_ERROR_MESSAGE;
import static common.AccountMessage.USERNAME_IS_TAKEN_MESSAGE;
import static common.AccountMessage.SAVED_MESSAGE;

/**
 * Tests the correctness of Account signup, login, and logout function.
 */
public class AccountTest {
    private final String VALID_USERNAME = "validusername";
    private final String VALID_PASSWORD = "validpassword";
    private final String INVALID_USERNAME = "invalidusername";
    private final String INVALID_PASSWORD = "wrongpassword";
    private final String NEW_VALID_USERNAME = "validuser";
    private final String NEW_VALID_PASSWORD = "validpassword";
    private final String SPECIAL_CHARACTERS_USERNAME = "special@username";
    private final String INVALID_LENGTH_PASSWORD = "pass";

    @Test
    void testValidLogin() {
        Account account = new Account(VALID_USERNAME, VALID_PASSWORD);
        account.signup();
        assertEquals("Login successfully.", account.login());
        deleteAccountAfterTest(account);
        deleteStorageFileAfterTest();
    }

    @Test
    void testInvalidUsernameLogin() {
        Account account = new Account(VALID_USERNAME, VALID_PASSWORD);
        account.signup();
        Account invalidAccount = new Account(INVALID_USERNAME, VALID_PASSWORD);
        assertEquals("Invalid username or password.", invalidAccount.login());
        deleteAccountAfterTest(account);
        deleteStorageFileAfterTest();
    }

    @Test
    void testInvalidPasswordLogin() {
        Account account = new Account(VALID_USERNAME, VALID_PASSWORD);
        account.signup();
        Account invalidAccount = new Account(VALID_USERNAME, INVALID_PASSWORD);
        assertEquals("Invalid username or password.", invalidAccount.login());
        deleteAccountAfterTest(account);
        deleteStorageFileAfterTest();
    }

    void deleteAccountAfterTest(Account newAccount) {
        boolean found = false;
        try {
            FileReader reader = new FileReader(SECURITY_STORAGE_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(newAccount.getAccountName())
                        && parts[1].equals(newAccount.getPasswordHash())) {
                    found = true;
                    continue;
                }
                lines.add(line);
            }
            bufferedReader.close();
            reader.close();
            if (found) {
                FileWriter writer = new FileWriter(SECURITY_STORAGE_FILE_PATH);
                for (String l : lines) {
                    writer.write(l + "\n");
                }
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void deleteStorageFileAfterTest() {
        File storageFile = new File(expensesStorageFilePath);
        storageFile.delete();
    }

    @Test
    void testValidSignUp() {
        Account newAccount = new Account(NEW_VALID_USERNAME, NEW_VALID_PASSWORD);
        assertEquals("User " + NEW_VALID_USERNAME + " has been created\n" +
                "Signup successfully.", newAccount.signup());
        deleteAccountAfterTest(newAccount);
        deleteStorageFileAfterTest();
    }

    @Test
    void testInvalidSignUpUserNameNonSpecialCharacters() {
        Account newAccount = new Account(SPECIAL_CHARACTERS_USERNAME, INVALID_PASSWORD);
        assertEquals(NO_SPECIAL_CHARACTERS_MESSAGE, newAccount.signup());
    }

    @Test
    void testInvalidSignUpUserNamePasswordLength() {
        Account newAccount = new Account(NEW_VALID_USERNAME, INVALID_LENGTH_PASSWORD);
        assertEquals(PASSWORD_LENGTH_ERROR_MESSAGE, newAccount.signup());
    }

    @Test
    void testInvalidSignUpUserNameIsTaken() {
        Account account = new Account(VALID_USERNAME, VALID_PASSWORD);
        account.signup();
        Account newAccount = new Account(VALID_USERNAME, INVALID_PASSWORD);
        assertEquals(USERNAME_IS_TAKEN_MESSAGE, newAccount.signup());
        newAccount = new Account(VALID_USERNAME, VALID_PASSWORD);
        assertEquals(USERNAME_IS_TAKEN_MESSAGE, newAccount.signup());
        deleteAccountAfterTest(newAccount);
        deleteStorageFileAfterTest();
    }

    @Test
    void testSaveLogOut() { //Test for both exit and logout command
        Account account = new Account(VALID_USERNAME, VALID_PASSWORD);
        account.signup();
        account.login();
        assertEquals(SAVED_MESSAGE, account.saveLogOut());
        deleteAccountAfterTest(account);
        deleteStorageFileAfterTest();
    }
}
