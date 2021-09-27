package by.epamtc.jwd.model;

import java.time.LocalDateTime;

public abstract class Bank {

    private String name;
    private LocalDateTime lastCurrencyUpdate;
    private String country;

    public Bank(String name, LocalDateTime lastCurrencyUpdate, String country) {
        this.name = name;
        this.lastCurrencyUpdate = lastCurrencyUpdate;
        this.country = country;
    }

    public Bank() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastCurrencyUpdate() {
        return lastCurrencyUpdate;
    }

    public void setLastCurrencyUpdate(LocalDateTime lastCurrencyUpdate) {
        this.lastCurrencyUpdate = lastCurrencyUpdate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", lastCurrencyUpdate=" + lastCurrencyUpdate +
                ", country='" + country + '\'' +
                '}';
    }
}
