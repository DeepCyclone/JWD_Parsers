package by.epamtc.jwd.parsers;

import by.epamtc.jwd.exceptions.ParserException;
import by.epamtc.jwd.model.CommercialBank;
import by.epamtc.jwd.model.NationalBank;

import java.util.Set;

public abstract class AbstractBuilder {
    public abstract Set<NationalBank> getNationalBanks();
    public abstract Set<CommercialBank> getCommercialBanks();
    public abstract void buildBankSets(String fileName) throws ParserException;
}
