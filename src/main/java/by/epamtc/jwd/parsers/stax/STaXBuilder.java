package by.epamtc.jwd.parsers.stax;

import by.epamtc.jwd.model.*;
import by.epamtc.jwd.parsers.AbstractBuilder;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class STaXBuilder extends AbstractBuilder {
    private Set<CommercialBank> commercialBanks;
    private Set<NationalBank> nationalBanks;
    private XMLInputFactory inputFactory;

    public STaXBuilder() {
        commercialBanks = new HashSet<>();
        nationalBanks = new HashSet<>();
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public Set<NationalBank> getNationalBanks() {
        return nationalBanks;
    }

    @Override
    public Set<CommercialBank> getCommercialBanks() {
        return commercialBanks;
    }

    public void buildBankSets(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if ("commercialBank".equals(name)) {
                        CommercialBank cb = buildCommercialBank(reader);
                        commercialBanks.add(cb);
                    }
                    if ("nationalBank".equals(name)) {
                        NationalBank nb = buildNationalBank(reader);
                        nationalBanks.add(nb);
                    }
                }
            }
        }
        catch (XMLStreamException ex) {
            System.err.println("StAX parsing error! " + ex.getMessage());
        }
        catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found! " + ex);
        }
        finally {
            if (inputStream != null) {
                try {
                inputStream.close();
                } catch (IOException e) {
                    System.err.println("Impossible close file " + fileName + " : " + e);
                }
                catch (Exception e){
                    System.out.println("Exception " + e);
                }
            }


        }
    }

    private NationalBank buildNationalBank(XMLStreamReader reader) throws XMLStreamException {
        NationalBank nb = new NationalBank();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (BankEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            nb.setName(getXMLText(reader));
                            break;
                        case LASTCURRENCYUPDATE:
                            nb.setLastCurrencyUpdate(LocalDateTime.parse(getXMLText(reader)));
                            break;
                        case CANEMITMONEY:
                            nb.setCanEmitMoney(Boolean.parseBoolean(getXMLText(reader).toUpperCase(Locale.ROOT)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if ("nationalBank".equals(name)) {
                        return nb;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown el");
    }

    private CommercialBank buildCommercialBank(XMLStreamReader reader) throws XMLStreamException {
        CommercialBank cb = new CommercialBank();
        String depositor = reader.getAttributeValue(null, "depositor");
        String country = reader.getAttributeValue(null, "country");
        if(!"".equals(depositor) && depositor!=null){
            cb.setDepositor(depositor);
        }
        if(!"".equals(country) && country!=null){
            cb.setCountry(country);
        }
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (BankEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            cb.setName(name);
                            break;
                        case LASTCURRENCYUPDATE:
                            cb.setLastCurrencyUpdate(LocalDateTime.parse(getXMLText(reader)));
                            break;
                        case TYPE:
                            cb.setType(Type.valueOf(getXMLText(reader).toUpperCase(Locale.ROOT)));
                            break;
                        case ACCOUNTID:
                            cb.setAccountID(getXMLText(reader));
                            break;
                        case AMOUNTONDEPOSIT:
                            cb.setAmountOnDeposit(new BigDecimal(getXMLText(reader)));
                            break;
                        case HASSPECIFIEDACCOUNTS:
                            cb.setHasSpecificAccounts(Boolean.parseBoolean(getXMLText(reader).toUpperCase(Locale.ROOT)));
                            break;
                        case PROFITABILITY:
                            cb.setProfitability(new BigDecimal(getXMLText(reader)));
                            break;
                        case TIMECONSTRAINTS:
                            cb.setTimeConstraints(Integer.parseInt(getXMLText(reader)));
                            break;

                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if ("commercialBank".equals(name)) {
                        return cb;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Student");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}

