# User Guide

##### Table of Contents
* [User Guide](#user-guide)
  * [Introduction](#introduction)
  * [Quick Start](#quick-start)
  * [Features](#features)
    * [Get helpful instruction: `help`](#get-helpful-instruction--help)
    * [Adding an expense: `add`](#adding-an-expense--add)
    * [Listing all expenses: `list`](#listing-all-expenses--list)
    * [Deleting an expense entry: `delete`](#deleting-an-expense-entry--delete)
    * [Calculating total expenses: `total`](#calculating-total-expenses--total)
    * [Sorting all current expenses: `sort`](#sorting-all-current-expenses--sort)
    * [Obtaining expenses: `category`](#obtaining-expenses--category)
    * [Finding expenses: `find`](#finding-expenses--find)
    * [Generating overview: `overview`](#generating-overview--overview)
    * [Exiting the program: `exit`](#exiting-the-program--exit)
  * [FAQ](#faq)
  * [Command Summary](#command-summary)

## Introduction

Our Expense Tracker CLI software ("ET" for short) allows users to create their own bookmakers and helps
them keep track of their expense based on category, time, amount, and currency.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `ET` from [here](https://github.com/AY2223S2-CS2113-T13-2/tp/releases).
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tp.jar` command to run
   the application.
4. Enter your name when prompted in the command box and press enter.
5. Type the command in the command box and press Enter to execute it.

   Some example commands you can try:

    * `add amt/100 t/15-03-2023`: Add an expense entry with a value of SGD100.0 and a date of 15th March 2023 to the
      expense list.
    * `list`: List all expenses in the expense list.
    * `delete 1`: Delete the first entry in the expense list.
    * `sort D`: Sort the expenses by date.
    * `category food`: Find all expenses with food category.
    * `find 2.5`: Find all expenses with information of 2.5.

## Features

### Get helpful instruction: `help`

List all available commands user can type in the software, and give instructions and examples to help user get
familiar with our software

Format: `help`

### Adding an expense: `add`

Adds a new item to the list of expenses.

Format: `add amt/EXPENSE_AMOUNT t/TIME [cat/EXPENSE_CATEGORY] [cur/EXPENSE_CURRENCY]`

* The `EXPENSE_AMOUNT` must be an integer or decimal number without comma.
* The `TIME` must be in the DD-MM-YYYY format.
    * Future dates beyond today's date and Past dates before 1981 are invalid.
    * A warning will be shown if the input date has been adjusted as such a date may not exist for that particular
      month or year.
* The `EXPENSE_CATERGORY` can be any string that does not contain a whitespace symbol.
* The `EXPENSE_CURRENCY` has 22 currencies to choose from.
    * The list of available currencies are
      `SGD`, `EUR`, `GBP`, `USD`, `AUD`, `CAD`, `CNY`, `HKD`, `INR`, `IDR`, `JPY`, `KRW`, `MYR`, `TWD`, `NZD`, `PHP`,
      `QAR`, `SAR`, `CHF`, `THB`, `AED`, `VND`.
    * If the input currency is not found in the list of currencies
      available, the currency will default to SGD and a warning will be shown.
* The command parameters may be entered in any order. If multiple of the same parameter type is inputted, such as in
  `add amt/10 amt/100 t/11-11-2023` only the first parameter `amt/10` of its kind is used.
    * A warning will be shown if an
      invalid input type is specified.

Example of usage:

`add amt/10 t/11-11-2022`

`add amt/9.5 t/01-11-2022 cat/food cur/USD`

Expected output:

```
____________________________________________________________
The following expense is successfully added:
SGD10.00 cat:uncategorized date:11/11/2022
Now you have 1 expense in the list.
____________________________________________________________
```

```
____________________________________________________________
The following expense is successfully added:
    USD9.50 cat:food date:01/11/2022
Now you have 2 expenses in the list.
____________________________________________________________
```

### Listing all expenses: `list`

List all tracked expenses in the expense list in the order of input (First input first print).

Format: `list`

* Will display `Sorry, there are no expenses tracked currently.` if expense list is empty.

Example of usage:

`list`

Expected output:

```
____________________________LIST____________________________
1.SGD10.00 cat:uncategorized date:11/11/2022
2.SGD24.00 cat:uncategorized date:02/02/2022
Now you have 2 expenses in the list.
____________________________________________________________
```

### Deleting an expense entry: `delete`

Delete expense entry with index X in the expense list.

Format: `delete INDEX`

* INDEX is entry number INDEX in the expense list displayed with `list` command, following base-1 indexing.
* Will display `Invalid expense index. Please try again.` if INDEX is greater than number of items in the list.

Example of usage:

`delete 1`

Expected output:

```
____________________________________________________________
The following expense is successfully deleted:
    SGD10.00 cat:uncategorized date:11/11/2022
Now you have 1 expense in the list.
____________________________________________________________
```

### Calculating total expenses: `total`

Calculates the total expenses in the expense list in `SGD`.

Format `total`

* Whenever an entry is added into the expense list, the exchange rate for the date before the input date is recorded.
  As exchange rate data is unavailable on weekends and public holidays, the exchange rate of the previous working day
  is taken instead.
* If no internet access is available, a preset exchange rate is taken instead.

Example of usage:

`total`

Expected output:

```
____________________________________________________________
Your total expenses add up to:
    SGD23.41
____________________________________________________________
```

### Sorting all current expenses: `sort`

Sorting all current expenses in the expense list based on sortBy criteria.

Format: `sort SORTBY`

* SORTBY represents the criteria the user want to sort their expenses list and display by.
* If user wants to sort the expenses list by Date, he/she should enter "D".
* If user wants to sort the expenses list by Category through alphabet order, he/she should enter "C".

Example of usage:

`sort D`

`sort C`

Example of output (sort D):

```
____________________________________________________________
Date: 02/02/2012
1.SGD2.50 cat:food
2.USD2.50 cat:food
Date: 02/02/2013
1.USD2.50 cat:eat
____________________________________________________________
```

### Obtaining expenses: `category`

Obtain all the expenses with specified category, also obtain the category set to tell user what categories exist now.

Format: `category CATEGORY`

* CATEGORY represents the category that user want.
* If the category doesn't belong to the categories that user have entered before, it will tell the user to switch to
  another one.
* The method also will tell user how many categories they have, and also what are these categories, to better help
  them with future obtaining purpose
* Case sensitivity doesn't matter!

Example of usage:

`category food`

`category clothes`

### Finding expenses: `find`

Find all expenses with the specified information, regardless whether this information can form a meaningful word or
match the expense attribute.

Format: `find INFO`

* INFO represents the information that the user want to find.
* If none of the expenses have such information, it will tell the user this case.
* Even if the information is not a word, let's say "f", the method still will find expenses that any of their attributes
  contain the character f.
* Case sensitivity doesn't matter!

Example of usage:

`find 2.5`

`find f`

`find SGD`

### Generating overview: `overview`

**Monthly overview**

Generate a monthly overview consisting of total expenses of the month and a breakdown of expenses based on category, 
from the most spent to the least. The currency for display is standardised to SGD.

Format: `overview MONTH YEAR`

- `MONTH` must be the standard English Month Name
- `YEAR` must be between 1981 and 2023 (inclusive), in integer format

Example of usage:
`overview June 2021`

Expected output:

```
Overview for JUNE 2021

    Total expenses: 80.55 SGD

    Breakdown of expenses by category in descending order by category sum:
    ----------------------------
     uncategorized 44.00 SGD
    ----------------------------
     food 26.55 SGD
    ----------------------------
     travel 10.00 SGD
    ----------------------------
```

**Yearly overview**

Generate a yearly overview consisting of total expenses of the year and a breakdown of monthly expenses, following
the order of natural month from January to December.
The currency for display is standardised to SGD.

Format: `overview YEAR`
- `YEAR` must be between 1981 and 2023 (inclusive), in integer format

Example of usage:
`overview 2022`

Expected output:
```
Yearly Overview for 2022

    Total expenses: 35.66 SGD

    Breakdown of expenses by month:
    ----------------------------
     JANUARY 0.00 SGD
    ----------------------------
     FEBRUARY 0.00 SGD
    ----------------------------
     MARCH 0.00 SGD
    ----------------------------
     APRIL 0.00 SGD
    ----------------------------
     MAY 10.00 SGD
    ----------------------------
     JUNE 0.00 SGD
    ----------------------------
     JULY 4.15 SGD
    ----------------------------
     AUGUST 0.00 SGD
    ----------------------------
     SEPTEMBER 0.00 SGD
    ----------------------------
     OCTOBER 21.51 SGD
    ----------------------------
     NOVEMBER 0.00 SGD
    ----------------------------
     DECEMBER 0.00 SGD
    ----------------------------
```

### Exiting the program: `exit`

Exits the program with all expenses saved.

Format: `exit`

Example of usage:

`exit`

## FAQ

### Do I require an internet connection to use ET?

- No an internet connection is not required. It is preferred as we use an exchange rate API to obtain our currency
exchange rates. However, a lack of internet connection will not hinder the use of our application.

### Why is my primary currency not available for use?

- Our selection of currencies is based on the currencies available in our exchange rate API, we apologise if
your currency is not available for use.

### I want to add a date beyond 1981, how do I do that?

- Our API only contains exchange rate information starting from 1981. For ET to effectively track expenses in different
currencies, we require a limit on how far back the date can be set.

  
## Command Summary

| Action                      | Command                                                                       |
|-----------------------------|-------------------------------------------------------------------------------|
| Add an expense              | `add amt/EXPENSE_AMOUNT t/TIME [cat/EXPENSE_CATEGORY] [cur/EXPENSE_CURRENCY]` |
| Delete an expense           | `delete INDEX`                                                                |
| List all expenses           | `list`                                                                        |
| Calculate total expenditure | `total`                                                                       |
| Sort                        | sort by category: `sort C`<br/>sort by date: `sort D`                         |
| View expenses by category   | `category CATEGORY`                                                           |
| Overview                    | monthly overview: `overview MONTH YEAR`<br/>yearly overview: `overview YEAR`  |
| Find expenses               | `find KEYWORD`                                                                |
| Exit ET                     | `exit`                                                                        |

