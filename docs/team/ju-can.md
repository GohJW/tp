# Project: Expense Tracker
The Expense Tracker ("ET" for short) allows users to create their own bookmakers and helps
them keep track of their expense based on category, date, amount, and currency.

## Summary of Contributions
### Code Contributed
Link to RepoSense Code for detailed code contribution:
[RepoSense Link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=ju-can&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).

### Enhancement Implemented
- **Delete Expenses**
  - Allows users to delete an expense that is added in the past.
  - Expenses are deleted based in the ID assigned to the expense in the expense list. Upon deleting, the ID of the
    expense deleted is recycled and the list is reordered.
  - PR: [#43](https://github.com/AY2223S2-CS2113-T13-2/tp/pull/43/files)

- **Storage**
  - Allows user to store and retrieve historical expenses.
  - The expense list is saved upon any changes as a JSON file.
  - Upon launching of the programme each time, the stored expenses are loaded from the JSON file.
  - PR: [#150](https://github.com/AY2223S2-CS2113-T13-2/tp/pull/150),
  [#153](https://github.com/AY2223S2-CS2113-T13-2/tp/pull/153).


- **Overview**
  - Monthly Overview
    - Provides a summary of the monthly expenses of a specific month, including total amount of expenses 
      and a breakdown of sum of expenses by category. The list of sum by category is sorted in descending order.
    - The currency unit for total expenses and sum by category is SGD.
    - PR: [#91](https://github.com/AY2223S2-CS2113-T13-2/tp/pull/91)
    
<div style="page-break-after: always;"></div>

  - Yearly Overview
    - Provides a summary of the yearly expenses of a specific year, including total amount of expenses and a monthly 
      breakdown.
    - The currency unit for yearly total and monthly total is SGD.
    - PR: [#147](https://github.com/AY2223S2-CS2113-T13-2/tp/pull/147)

### Contributions to the UG
- Expected output for `add`, `list` and `delete`.
- User instruction, sample input and expected output for `overview`.
- Command summary.

### Contributions to the DG
- Detailed explanation and supporting diagrams on the mechanism of 
  - delete: PR [#67](https://github.com/AY2223S2-CS2113-T13-2/tp/pull/67)
  - monthly overview: PR [#100](https://github.com/AY2223S2-CS2113-T13-2/tp/pull/100)
  - yearly overview: PR [#167](https://github.com/AY2223S2-CS2113-T13-2/tp/pull/167)
  - storage: PR [#169](https://github.com/AY2223S2-CS2113-T13-2/tp/pull/169)

### Review/mentoring contributions
- Merged most of the Pull Requests after reviewing newly added code.
- Some PR reviews that I have given: [PR Review 1](https://github.com/AY2223S2-CS2113-T13-2/tp/pull/74),
  [PR Review 2](https://github.com/AY2223S2-CS2113-T13-2/tp/pull/54).
- Suggestion on logics and implementations of features of other teammates, including sorting and the usage of 
  storage under the account functionality.
- Setting of internal deadlines and checking on teammate's progress to ensure timely delivery of the end product.

