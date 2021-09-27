package by.epamtc.jwd.parsers.sax;

import by.epamtc.jwd.exceptions.ParserException;
import by.epamtc.jwd.model.CommercialBank;
import by.epamtc.jwd.model.NationalBank;
import by.epamtc.jwd.parsers.AbstractBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class SAXBuilder extends AbstractBuilder {
    private Set<NationalBank> nationalBanks;
    private Set<CommercialBank> commercialBanks;
    private BankHandler bankHandler;
    private XMLReader reader;

    public SAXBuilder() throws ParserException {
        bankHandler = new BankHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(bankHandler);
        } catch (SAXException e) {
            throw new ParserException(e.getMessage(),e);
        }
    }

    public Set<NationalBank> getNationalBanks() {
        return nationalBanks;
    }

    public Set<CommercialBank> getCommercialBanks(){
        return commercialBanks;
    }
    public void buildBankSets(String fileName) throws ParserException {
        try {
            reader.parse(fileName);
            nationalBanks = bankHandler.getNationalBanks();
            commercialBanks = bankHandler.getCommercialBanks();
        } catch (SAXException | IOException e){
            throw new ParserException(e.getMessage(),e);
        }
    }
}
