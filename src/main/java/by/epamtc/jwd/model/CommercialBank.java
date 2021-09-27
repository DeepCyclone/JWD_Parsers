package by.epamtc.jwd.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CommercialBank extends Bank{
    private Type type;
    private String accountID;
    private BigDecimal amountOnDeposit;
    private boolean hasSpecificAccounts;
    private BigDecimal profitability;
    private int timeConstraints;
    private String depositor;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                super.toString() +
                "type=" + type +
                ", accountID='" + accountID + '\'' +
                ", amountOnDeposit=" + amountOnDeposit +
                ", hasSpecificAccounts=" + hasSpecificAccounts +
                ", profitability=" + profitability +
                ", timeConstraints=" + timeConstraints +
                ", depositor='" + depositor + '\'' +
                '}';
    }

    public CommercialBank(String name, LocalDateTime lastCurrencyUpdate, String country, Type type, String accountID, BigDecimal amountOnDeposit, boolean hasSpecificAccounts, BigDecimal profitability, int timeConstraints, String depositor) {
        super(name, lastCurrencyUpdate, country);
        this.type = type;
        this.accountID = accountID;
        this.amountOnDeposit = amountOnDeposit;
        this.hasSpecificAccounts = hasSpecificAccounts;
        this.profitability = profitability;
        this.timeConstraints = timeConstraints;
        this.depositor = depositor;
    }

    public CommercialBank(Type type, String accountID, BigDecimal amountOnDeposit, boolean hasSpecificAccounts, BigDecimal profitability, int timeConstraints, String depositor) {
        this.type = type;
        this.accountID = accountID;
        this.amountOnDeposit = amountOnDeposit;
        this.hasSpecificAccounts = hasSpecificAccounts;
        this.profitability = profitability;
        this.timeConstraints = timeConstraints;
        this.depositor = depositor;
    }

    public CommercialBank() {
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public BigDecimal getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public void setAmountOnDeposit(BigDecimal amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    public boolean isHasSpecificAccounts() {
        return hasSpecificAccounts;
    }

    public void setHasSpecificAccounts(boolean hasSpecificAccounts) {
        this.hasSpecificAccounts = hasSpecificAccounts;
    }

    public BigDecimal getProfitability() {
        return profitability;
    }

    public void setProfitability(BigDecimal profitability) {
        this.profitability = profitability;
    }

    public int getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(int timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }
}
