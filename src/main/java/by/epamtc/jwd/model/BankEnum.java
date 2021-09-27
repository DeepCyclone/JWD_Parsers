package by.epamtc.jwd.model;

public enum BankEnum {
        NAME("name"),
        LASTCURRENCYUPDATE("lastCurrencyUpdate"),
        CANEMITMONEY("canEmitMoney"),
        TYPE("type"),
        ACCOUNTID("accountID"),
        AMOUNTONDEPOSIT("amountOnDeposit"),
        HASSPECIFIEDACCOUNTS("hasSpecifiedAccounts"),
        PROFITABILITY("Profitability"),
        TIMECONSTRAINTS("timeConstraints");

        private String value;
        private BankEnum(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
}
