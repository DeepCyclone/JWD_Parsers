package by.epamtc.jwd.model;

import java.time.LocalDateTime;

public class NationalBank extends Bank {
    public NationalBank(String name, LocalDateTime lastCurrencyUpdate, String country, boolean canEmitMoney) {
        super(name, lastCurrencyUpdate, country);
        this.canEmitMoney = canEmitMoney;
    }

    public NationalBank() {
    }

    public boolean isCanEmitMoney() {
        return canEmitMoney;
    }

    private boolean canEmitMoney;

    public void setCanEmitMoney(boolean canEmitMoney) {
        this.canEmitMoney = canEmitMoney;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                super.toString() +
                "canEmitMoney=" + canEmitMoney +
                '}';
    }
}
