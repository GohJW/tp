package seedu.duke;

import command.CommandDelete;
import command.CommandAdd;
import command.CommandCategory;
import command.CommandList;

import command.CommandTotal;
import command.CommandSort;
import command.CommandHelp;
import command.CommandFind;

import common.MessageList;
import command.overview.CommandOverview;

import data.Account;
import data.ExpenseList;
import data.Currency;

import parser.Parser;
import parser.ParserAccount;
import storage.Storage;

import java.util.Scanner;

public class Duke {
    protected static Storage storage;
    protected Parser parser;
    protected ExpenseList expenseList;
    protected Currency currency;

    /**
     * Initialize Duke and instantiate parser and account objects.
     */
    public Duke() {
        parser = new Parser();
        expenseList = new ExpenseList();
        currency = new Currency();
        storage = new Storage(expenseList);

    }

    public void run() {
        MessageList.printLogo();
        Scanner in = new Scanner(System.in);
        ParserAccount.initialize(in);
        MessageList.welcomeHelper();

        String input = "";
        while (in.hasNextLine()) {
            input = in.nextLine();
            if (input.equals("exit")) {
                ParserAccount.caseExit();
                break;
            }
            switch (parser.extractCommandKeyword(input)) {
            case "add":
                new CommandAdd(expenseList.getExpenseList(), parser.extractAddParameters(input), currency).execute();
                Account.autoSave();
                break;
            case "delete":
                new CommandDelete(expenseList.getExpenseList(), parser.extractIndex(input)).execute();
                Account.autoSave();
                break;
            case "list":
                new CommandList(expenseList.getExpenseList()).run();
                break;
            case "total":
                new CommandTotal(expenseList.getExpenseList()).execute();
                break;
            case "sort":
                new CommandSort(expenseList.getExpenseList(), parser.extractSortBy(input)).execute();
                break;
            case "category":
                new CommandCategory(expenseList.getExpenseList(), parser.extractCategory(input)).execute();
                break;
            case "logout":
                ParserAccount.caseLogOut(in);
                break;
            case "overview":
                new CommandOverview(expenseList.getExpenseList(),
                        parser.extractMonth(input), parser.extractYear(input)).execute();
                break;
            case "find":
                // Use the same parser function as category as it also need the input string from user
                new CommandFind(expenseList.getExpenseList(), parser.extractCategory(input)).execute();
                break;
            case "help":
                new CommandHelp().execute();
                break;
            default:
                System.out.println("Unknown command.");
            }
        }
        in.close();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }


}
