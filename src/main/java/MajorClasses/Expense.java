package MajorClasses;


import java.util.Objects;

public class Expense {
    protected double expenseAmount;
    protected String expenseTime;
    protected String description;
    protected Currency currencyType;

    public Expense() {
        this.expenseAmount = 0;
    }

    public Expense(double expenseAmount, String expenseTime, String description, Currency currencyType) {
        this.expenseAmount = expenseAmount;
        this.expenseTime = expenseTime;
        this.description = description;
        this.currencyType = currencyType;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public String getExpenseTime() {
        return expenseTime;
    }

    public String getDescription() {
        return description;
    }
    public Currency getCurrencyType() { return currencyType; }

    // The setter method will be used if the User want to change some information in their previous expense
    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public void setExpenseTime(String expenseTime) {
        this.expenseTime = expenseTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void printTask() {
        System.out.println(this.currencyType.toString() + this.expenseAmount + " ");
        System.out.println(this.expenseTime + " " + this.description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return Objects.equals(this.getDescription(), ((Expense) obj).getDescription())
                && this.getExpenseAmount() == ((Expense) obj).getExpenseAmount()
                && Objects.equals(this.getExpenseTime(), ((Expense) obj).getExpenseTime());
    }


}

