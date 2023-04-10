package command;

import static common.MessageList.MESSAGE_DIVIDER;

public class CommandHelp extends Command {
    public static final String COMMAND_NAME = "help";

    public CommandHelp() {
        super(COMMAND_NAME);
    }

    /**
     * This method will print all allowed method that user can call and get some idea about how to use this software
     */
    @Override
    public CommandRes execute() {
        System.out.println("Here are all available commands you can call!");
        System.out.println("[xxx] represents the input you need to put in, " +
                "and don't forget the spaces inside the format instruction");
        System.out.println("{xx} indicates that this information is optional");
        System.out.println(MESSAGE_DIVIDER);

        System.out.println("Add Command: You can add new expense to your list");
        System.out.println("Format: add amt/[Amount] t/[Time]{ cat/[Category]}{ cur/[Currency]}");
        System.out.println("Example: add amt/9.5 t/01-11-2022 cat/food cur/USD");
        System.out.println(MESSAGE_DIVIDER);

        System.out.println("List Command: List all tracked expenses in the order of input");
        System.out.println("Format: list");
        System.out.println("Example: list");
        System.out.println(MESSAGE_DIVIDER);

        System.out.println("Delete Command: Delete expense with corresponding index");
        System.out.println("Format: delete [Index]");
        System.out.println("Example: delete 1");
        System.out.println(MESSAGE_DIVIDER);

        System.out.println("Total Command: Calculate the sum of total expenses in SGD");
        System.out.println("Format: total");
        System.out.println("Example: total");
        System.out.println(MESSAGE_DIVIDER);

        System.out.println("Sort Command: Sort all the expenses by date or category");
        System.out.println("Format: sort [SortBy, which can be D(Date) or C(Category)]");
        System.out.println("Example: sort D");
        System.out.println(MESSAGE_DIVIDER);

        System.out.println("Category Command: Get all expenses with specified category");
        System.out.println("Format: category [Category]");
        System.out.println("Example: category food");
        System.out.println(MESSAGE_DIVIDER);

        System.out.println("Find Command: Find the expenses with information you have");
        System.out.println("Format: find [Information]");
        System.out.println("Example: find f");
        System.out.println(MESSAGE_DIVIDER);

        System.out.println("Monthly Overview Command: Generate a monthly overview based on category," +
                " with expenses standardized in SGD");
        System.out.println("Format: overview [Month, should be the standard English Month Name] [Year]");
        System.out.println("Example: overview June 2021");
        System.out.println(MESSAGE_DIVIDER);

        System.out.println("Yearly Overview Command: Generate a yearly overview based on natural month," +
                " with expenses standardized in SGD");
        System.out.println("Format: overview [Year]");
        System.out.println("Example: overview 2021");
        System.out.println(MESSAGE_DIVIDER);

        System.out.println("Exit Command: Exit the software with all your expenses stored automatically");
        System.out.println("Format: exit");
        System.out.println("Example: exit");
        System.out.println(MESSAGE_DIVIDER);

        System.out.println("Enjoy!");

        return null;

    }


}
