package by.epamtc.jwd.parsers.dom;

import by.epamtc.jwd.exceptions.ParserException;
import by.epamtc.jwd.model.CommercialBank;
import by.epamtc.jwd.model.NationalBank;
import by.epamtc.jwd.model.Type;
import by.epamtc.jwd.parsers.AbstractBuilder;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class DOMBuilder extends AbstractBuilder {
    private Set<NationalBank> nationalBanks;
    private Set<CommercialBank> commercialBanks;
    private DocumentBuilder docBuilder;

    public DOMBuilder() throws ParserException {
        nationalBanks = new HashSet<>();
        commercialBanks = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ParserException(e.getMessage(),e);
        }
    }

    @Override
    public Set<NationalBank> getNationalBanks() {
        return nationalBanks;
    }

    @Override
    public Set<CommercialBank> getCommercialBanks() {
        return commercialBanks;
    }

    public void buildBankSets(String fileName) throws ParserException {
        Document doc = null;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList commercialBanksList = root.getElementsByTagName("commercialBank");
            NodeList nationalBanksList = root.getElementsByTagName("nationalBank");
            for (int i = 0; i < commercialBanksList.getLength(); i++) {
                Element studentElement = (Element) commercialBanksList.item(i);
                CommercialBank cb = buildCommercialBank(studentElement);
                commercialBanks.add(cb);
            }

            for (int i = 0; i < nationalBanksList.getLength(); i++) {
                Element studentElement = (Element) nationalBanksList.item(i);
                NationalBank nb = buildNationalBank(studentElement);
                nationalBanks.add(nb);
            }

        } catch (IOException | SAXException e){
            throw new ParserException(e.getMessage(),e);
        }
    }

    private CommercialBank buildCommercialBank(Element commercialBank){
        CommercialBank cb = new CommercialBank();
        cb.setName(getElementTextContent(commercialBank,"name"));
        cb.setLastCurrencyUpdate
                (LocalDateTime.parse(getElementTextContent(commercialBank,"lastCurrencyUpdate")));
        cb.setType(Type.valueOf(getElementTextContent(commercialBank,"type").toUpperCase(Locale.ROOT)));
        cb.setAccountID(getElementTextContent(commercialBank,"accountID"));
        cb.setAmountOnDeposit(new BigDecimal(getElementTextContent(commercialBank,"amountOnDeposit")));
        cb.setHasSpecificAccounts(Boolean.parseBoolean(getElementTextContent(commercialBank,"hasSpecifiedAccounts")));
        cb.setProfitability(new BigDecimal(getElementTextContent(commercialBank,"Profitability")));
        cb.setTimeConstraints(Integer.parseInt(getElementTextContent(commercialBank,"timeConstraints")));
        if(commercialBank.hasAttribute("depositor")){
            cb.setDepositor(commercialBank.getAttribute("depositor"));
        }
        if(commercialBank.hasAttribute("country")) {
            cb.setCountry(commercialBank.getAttribute("country"));
        }

        return cb;
    }

    private NationalBank buildNationalBank(Element nationalBank){
        NationalBank nb = new NationalBank();
        nb.setName(getElementTextContent(nationalBank,"name"));
        nb.setLastCurrencyUpdate
                (LocalDateTime.parse(getElementTextContent(nationalBank,"lastCurrencyUpdate")));
        nb.setCanEmitMoney(Boolean.parseBoolean(getElementTextContent(nationalBank,"canEmitMoney")));

        return nb;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
