package parser;

import data.Account;
import java.util.Scanner;

import static common.AccountMessage.INVALID_ACCOUNT_MESSAGE;
import static common.AccountMessage.NON_EXISTING_ACCOUNT_MESSAGE;
import static common.AccountMessage.FAIL_READING_USERNAME_MESSAGE;
import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.ACCOUNT_MESSAGE;
import static common.MessageList.LOGOUT_MESSAGE;
import static common.MessageList.EXIT_MESSAGE;
import static data.Account.saveLogOut;
import static data.ExpenseList.showToUser;
import static java.lang.System.exit;

/**
 * This class is responsible for parsing user input related to accounts, such as login, signup, logout and exit.
 *
 * It contains methods for each of these actions, which take a Scanner as input and interact with the user through
 * the console.
 */
public class ParserAccount {
    /**
     * This method handles the login process of an existing account.
     * It prompts the user for their username and password, creates an Account object,
     * and calls the login method to verify the user's credentials.
     *
     * @param scanner A Scanner object used to read user input.
     */
    private static void caseLogIn(Scanner scanner) {
        System.out.println("username");
        String user = scanner.nextLine();
        System.out.println("password");
        String password = scanner.nextLine();
        Account existingAccount = new Account(user, password);
        String res = existingAccount.login();
        System.out.println(res);
        if (res.equals(INVALID_ACCOUNT_MESSAGE)
            || res.equals(NON_EXISTING_ACCOUNT_MESSAGE)
            || res.equals(FAIL_READING_USERNAME_MESSAGE)) {
            initialize(scanner);
        }
    }

    /**
     * This method handles the sign up process of a new account.
     * It prompts the user for their desired username and password,
     * creates an Account object, and calls the signup method to create the new account.
     *
     * @param scanner A Scanner object used to read user input.
     */
    public static void caseSignUp(Scanner scanner) {
        System.out.println("username");
        String user = scanner.nextLine();
        System.out.println("password");
        String password = scanner.nextLine();
        Account newAccount = new Account(user, password);
        System.out.println(newAccount.signup());
    }

    /**
     * This method handles the log out process of an account.
     * It saves the current account's state and displays a message to the user that they have been logged out.
     *
     * @param scanner A Scanner object used to read user input.
     */
    public static void caseLogOut(Scanner scanner) {
        System.out.println(saveLogOut());
        showToUser(LOGOUT_MESSAGE);
        initialize(scanner);
    }

    /**
     * This method handles the exiting process of the application.
     * It saves the current account's state and displays a message to the user that the application is exiting.
     */
    public static void caseExit() {
        System.out.println(saveLogOut());
        showToUser(EXIT_MESSAGE);
    }

    /**
     * This method initializes the ParserAccount class and displays the main menu to the user.
     * It prompts the user to either log in, sign up, or exit the application.
     *
     * @param in A Scanner object used to read user input.
     */
    public static void initialize(Scanner in) {
        do {
            showToUser(MESSAGE_DIVIDER, ACCOUNT_MESSAGE, MESSAGE_DIVIDER);
            String input = "";
            if (in.hasNextLine()) {
                input = in.nextLine();
            }
            if (input.equals("login")) {
                // get login details
                caseLogIn(in);
                break;
            } else if (input.equals("signup")) {
                // get register details
                caseSignUp(in);
            } else if (input.equals("exit")) {
                exit(0);
            } else {
                // invalid input, tell them to try again
                System.out.println("invalid option, chose login or signup!");
                initialize(in);
                break;
            }
        } while (true);
    }

}




