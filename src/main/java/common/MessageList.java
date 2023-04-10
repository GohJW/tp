package common;

public class MessageList {
    public static final String MESSAGE_DIVIDER =
            "_____________________________________________________________________________________";
    public static final String MESSAGE_DIVIDER_LIST =
            "_________________________________________LIST_________________________________________";
    public static final String MESSAGE_DIVIDER_SORTEDLIST =
            "______________________________________SORTEDLIST______________________________________";
    public static final String MESSAGE_DIVIDER_CATEGORY =
            "_______________________________________CATEGORY_______________________________________";
    public static final String MESSAGE_DIVIDER_FIND =
            "_________________________________________FIND_________________________________________";
    public static final int OFFSET = 1;

    public static final String SUCCESSFUL_DELETE = "The following expense is successfully deleted:";
    public static final String SUCCESSFUL_ADD = "The following expense is successfully added:";

    public static final String TAB = "    ";
    public static final String ACCOUNT_MESSAGE = "> Type \"login\" to start manage your expense. \n" +
            "> Haven't got your account yet? Type \"signup\" to create your account. \n" +
            "> Type \"exit\" to instantly terminate the program.";

    public static final String EXIT_MESSAGE = "Goodbye! Hope to see you again soon! \n";
    public static final String LOGOUT_MESSAGE = "You are logged out successfully!";

    public static final String WHITESPACE = " ";
    public static final String PERIOD = ".";

    /**
     * This method is used to remind the user to use commandHelper the first time after they log in the software.
     */
    public static void welcomeHelper() {
        System.out.println("You can type 'help' and press enter button to get the helper!");
    }

    public static void printLogo() {
        System.out.println();
        System.out.println("" +
                "        *******     **********" + System.lineSeparator() +
                "        *******     **********" + System.lineSeparator() +
                "        ***            ***" + System.lineSeparator() +
                "        ******         ***" + System.lineSeparator() +
                "        ******         ***" + System.lineSeparator() +
                "        ******         ***" + System.lineSeparator() +
                "        ***            ***" + System.lineSeparator() +
                "        ***            ***" + System.lineSeparator() +
                "        *******        ***" + System.lineSeparator() +
                "        *******        ***");
        System.out.println();
    }
}
