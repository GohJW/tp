package data;

import storage.Storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import java.util.regex.Pattern;

import static common.AccountMessage.MIN_PASSWORD_LENGTH;
import static common.AccountMessage.INVALID_ACCOUNT_MESSAGE;
import static common.AccountMessage.NO_SPECIAL_CHARACTERS_MESSAGE;
import static common.AccountMessage.NO_NULL_PASSWORD_MESSAGE;
import static common.AccountMessage.PASSWORD_LENGTH_ERROR_MESSAGE;
import static common.AccountMessage.USERNAME_IS_TAKEN_MESSAGE;
import static common.AccountMessage.IO_EXCEPTION_SIGNUP_MESSAGE;
import static common.AccountMessage.VALID_ACCOUNT_MESSAGE;
import static common.AccountMessage.NON_EXISTING_ACCOUNT_MESSAGE;
import static common.AccountMessage.IO_EXCEPTION_LOGIN_MESSAGE;
import static common.AccountMessage.FAIL_READING_USERNAME_MESSAGE;
import static common.AccountMessage.SAVED_MESSAGE;

/**
 * This class represents a user account and provides functionality for signup, login,
 * and accessing the user's expense list.
 */
public class Account {

    public static ExpenseList account;
    public static final String SECURITY_STORAGE_FILE_PATH = "userList.txt";
    protected static String expensesStorageFilePath;
    protected static String accountName;
    protected static Storage storage;
    private static String passwordHash;
    private static final Pattern USERNAME_PATTERN = Pattern.compile("[a-zA-Z0-9]+");

    /**
     * Constructs a new Account object with the specified account name and password.
     *
     * @param accountName The name of the user's account.
     * @param password The user's password.
     */
    public Account(String accountName, String password) {
        this.accountName = accountName;
        this.passwordHash = hashPassword(password);
        this.account = new ExpenseList();
        storage = new Storage(account);
        expensesStorageFilePath = accountName + ".json";

        // Read the expense list from the file with the file path of expensesStorageFilePath if it exists
        try (BufferedReader br = new BufferedReader(new FileReader(expensesStorageFilePath))) {
            account = new ExpenseList(br);
        } catch (FileNotFoundException e) {
            // The file does not exist, which means there is no saved expense list
        } catch (IOException e) {
            System.out.println("Error: Failed to read expense list from file.");
        }
    }

    /**
     * Returns the name of the user's account.
     *
     * @return The name of the user's account.
     */
    public String getAccountName() {
        return this.accountName;
    }

    /**
     * Returns the hashed version of the user's password.
     *
     * @return The hashed version of the user's password.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Returns the user's expense list.
     *
     * @return The user's expense list.
     */
    public ExpenseList getExpenseList() {
        return account;
    }

    /**
     * Signs up the user with the given account name and password. Writes the user's login
     * information to a file and creates a new file for storing the user's expense list.
     *
     * @return A message indicating whether the signup was successful or if there was an error.
     */
    public String signup() {
        // Check if username contains special characters
        if (!USERNAME_PATTERN.matcher(accountName).matches()) {
            return NO_SPECIAL_CHARACTERS_MESSAGE;
        } else if (passwordHash == null) {
            return NO_NULL_PASSWORD_MESSAGE;
        } else if (passwordHash.length() < MIN_PASSWORD_LENGTH) {
            return PASSWORD_LENGTH_ERROR_MESSAGE;
        } else if (isUsernameTaken()) {  // Check if username is taken
            return USERNAME_IS_TAKEN_MESSAGE;
        } else {
            try {
                storage.createFile(expensesStorageFilePath);
                FileWriter pw = new FileWriter(SECURITY_STORAGE_FILE_PATH, true);
                pw.write(accountName + "," + passwordHash + "\n");
                pw.close();
                return ("User " + accountName + " has been created\n" + "Signup successfully.");
            } catch (IOException e) {
                return IO_EXCEPTION_SIGNUP_MESSAGE;
            }
        }
    }

    /**
     * Attempts to log in the user with the account name and password associated
     * with the account object.
     *
     * @return A message indicating the status of the log in attempt as a String.
     */
    public String login() {
        boolean found = false;
        try {
            FileReader reader = new FileReader(SECURITY_STORAGE_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(accountName) && parts[1].equals(passwordHash)) {
                    found = true;
                    break;
                }
            }
            bufferedReader.close();
            reader.close();
            if (found) {
                storage.loadExpenses(expensesStorageFilePath);
                return VALID_ACCOUNT_MESSAGE;
            } else {
                return INVALID_ACCOUNT_MESSAGE;
            }
        } catch (FileNotFoundException e) {
            return (NON_EXISTING_ACCOUNT_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            return (IO_EXCEPTION_LOGIN_MESSAGE);
        }
    }

    /**
     * Checks if the account name associated with the account object is already taken
     * by another user.
     *
     * @return True if the account name is taken, false otherwise.
     */
    private boolean isUsernameTaken() {
        try {
            BufferedReader br =
                     new BufferedReader(new FileReader(SECURITY_STORAGE_FILE_PATH));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(accountName)) {
                    return true;
                }
            }
            return false;
        } catch (FileNotFoundException e) {
            // Username file not found, which means the username is not taken
            return false;
        } catch (IOException e) {
            System.out.println(FAIL_READING_USERNAME_MESSAGE);
            return false;
        }
    }

    /**
     * Hashes the specified password to string type
     *
     * @param password The password as a String.
     * @return The hashed password as a String.
     */
    private String hashPassword(String password) {
        // Restrict password to numbers only if hashing is not supported
        char[] passwordChars = password.toCharArray();
        for (char c : passwordChars) {
            if (!Character.isDigit(c)) {
                return password; // Password contains non-digit characters
            }
        }
        return String.valueOf(passwordChars);
    }

    /**
     * Log out of the account and save the expense list associated with the account to a file with the
     * file path of expensesStorageFilePath and clears the account's expense list.
     *
     * @return A message indicating that the expense list has been saved as a String.
     */
    public static String saveLogOut() {
        storage.saveExpenses(expensesStorageFilePath);
        account.clear();
        account = null;
        return SAVED_MESSAGE;
    }

    /**
     * Auto-save the expense list associated with the account to a file with the
     * file path of expensesStorageFilePath.
     */
    public static void autoSave() {
        storage.saveExpenses(expensesStorageFilePath);
    }
}


