package com.noobsquad.farmshare.Models;

public class Expense {

    String expenseName;
    String expenseDate;
    Double expense;

    public Expense(String expenseName, String expenseDate, Double expense) {
        this.expenseName = expenseName;
        this.expenseDate = expenseDate;
        this.expense = expense;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }
}
