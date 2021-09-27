package by.epamtc.jwd.parsers.sax;

import by.epamtc.jwd.model.BankEnum;
import by.epamtc.jwd.model.CommercialBank;
import by.epamtc.jwd.model.NationalBank;
import by.epamtc.jwd.model.Type;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankHandler extends DefaultHandler {
    private Set<CommercialBank> commercialBanks;
    private Set<NationalBank> nationalBanks;
    private CommercialBank commercialBank = null;
    private NationalBank nationalBank = null;
    private BankEnum currentEnum = null;
    private EnumSet<BankEnum> withText;
    private String currentElement;

    public BankHandler() {
        commercialBanks = new HashSet<CommercialBank>();
        nationalBanks = new HashSet<NationalBank>();
        withText = EnumSet.range(BankEnum.NAME, BankEnum.TIMECONSTRAINTS);
    }

    public Set<CommercialBank> getCommercialBanks() {
        return commercialBanks;
    }
    public Set<NationalBank> getNationalBanks() {
        return nationalBanks;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("commercialBank".equals(localName)) {
            currentElement = localName;
            commercialBank = new CommercialBank();
            if (attrs.getLength() != 0) {
                commercialBank.setDepositor(attrs.getValue(0));
                if (attrs.getLength() == 2) {
                    commercialBank.setCountry(attrs.getValue(1));
                }
            }
        } else if ("nationalBank".equals(localName)) {
            currentElement = localName;
            nationalBank = new NationalBank();
        } else {
            try {
                BankEnum temp = BankEnum.valueOf(localName.toUpperCase());
                if (withText.contains(temp)) {
                    currentEnum = temp;
                }

            } catch (IllegalArgumentException e) {

            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if ("commercialBank".equals(localName)) {
            currentElement = "";
            commercialBanks.add(commercialBank);
        } else if ("nationalBank".equals(localName)) {
            currentElement = "";
            nationalBanks.add(nationalBank);
        }

    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null && !s.equals("")) {
            if ("commercialBank".equals(currentElement)) {
                switch (currentEnum) {
                    case NAME:
                        commercialBank.setName(s);
                        break;
                    case LASTCURRENCYUPDATE:
                        commercialBank.setLastCurrencyUpdate(LocalDateTime.parse(s));
                        break;
                    case TYPE:
                        commercialBank.setType(Type.valueOf(s.toUpperCase(Locale.ROOT)));
                        break;
                    case ACCOUNTID:
                        commercialBank.setAccountID(s);
                        break;
                    case AMOUNTONDEPOSIT:
                        commercialBank.setAmountOnDeposit(new BigDecimal(s));
                        break;
                    case HASSPECIFIEDACCOUNTS:
                        commercialBank.setHasSpecificAccounts(Boolean.valueOf(s));
                        break;
                    case PROFITABILITY:
                        commercialBank.setProfitability(new BigDecimal(s));
                        break;
                    case TIMECONSTRAINTS:
                        commercialBank.setTimeConstraints(Integer.valueOf(s));
                        break;
                    default:
                }
            } else if ("nationalBank".equals(currentElement)) {
                switch (currentEnum) {
                    case NAME:
                        nationalBank.setName(s);
                        break;
                    case LASTCURRENCYUPDATE:
                        nationalBank.setLastCurrencyUpdate(LocalDateTime.parse(s));
                        break;
                    case CANEMITMONEY:
                        nationalBank.setCanEmitMoney(Boolean.valueOf(s));
                        break;
                }
                currentEnum = null;
            }
        }
        }
    }

