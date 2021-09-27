package by.epamtc.jwd.controller.command;

import by.epamtc.jwd.model.CommercialBank;
import by.epamtc.jwd.model.NationalBank;
import by.epamtc.jwd.parsers.AbstractBuilder;
import by.epamtc.jwd.parsers.ParsersFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class ParseCommandTest {

    private static String Folder = "C:\\apache-tomcat-9.0.50-windows-x64\\apache-tomcat-9.0.50\\webapps\\XMLParser\\src\\test\\resources\\";
    private static String commercialBankXmlSchema = Folder + "CommercialBank.xml";
    private static String nationalBankXmlSchema = Folder +  "NationalBank.xml";
    private static String mixedBanksXmlSchema = Folder + "mixedBanks.xml";
    private static String xsdSchema = Folder + "banks.xsd";

    private static String expectedCommB = "CommercialBank{name='Colk Bank Inc', lastCurrencyUpdate=2021-08-22T16:59:59, country='USA'}type=BEFOREDEMAND, accountID='B27622', amountOnDeposit=132, hasSpecificAccounts=true, profitability=345, timeConstraints=2222, depositor='FBI'}";
    private static String expectedNatB = "NationalBank{name='SB', lastCurrencyUpdate=2021-05-24T10:59:59, country='null'}canEmitMoney=true}";
    private static String mixedBanksExpected = "CommercialBank{name='RF Bank Inc', lastCurrencyUpdate=2021-08-22T16:59:59, country='null'}type=URGENT, accountID='U22222', amountOnDeposit=132, hasSpecificAccounts=true, profitability=345, timeConstraints=2222, depositor='Flexus'}" +
                                                "NationalBank{name='SB', lastCurrencyUpdate=2021-05-24T10:59:59, country='null'}canEmitMoney=true}";
    @Test
    public void parseCommercialBankSAX(){
        try {
            AbstractBuilder SAX = ParsersFactory.getParser("SAX");
            SAX.buildBankSets(commercialBankXmlSchema);
            Set<CommercialBank> commercialBanks = SAX.getCommercialBanks();
            StringBuilder res = new StringBuilder();
            for(CommercialBank c:commercialBanks){
                res.append(c.toString());
            }
            assertEquals(expectedCommB,res.toString());
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void parseNationalBankSAX(){
        try {
            AbstractBuilder SAX = ParsersFactory.getParser("SAX");
            SAX.buildBankSets(nationalBankXmlSchema);
            Set<NationalBank> nationalBanks = SAX.getNationalBanks();
            StringBuilder res = new StringBuilder();
            for(NationalBank c:nationalBanks){
                res.append(c.toString());
            }
            assertEquals(expectedNatB,res.toString());
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void parseBanksSAX(){
        try {
            AbstractBuilder SAX = ParsersFactory.getParser("SAX");
            SAX.buildBankSets(mixedBanksXmlSchema);
            Set<NationalBank> nationalBanks = SAX.getNationalBanks();
            Set<CommercialBank> commercialBanks = SAX.getCommercialBanks();
            StringBuilder res = new StringBuilder();
            for(CommercialBank c:commercialBanks){
                res.append(c.toString());
            }
            for(NationalBank c:nationalBanks){
                res.append(c.toString());
            }
            assertEquals(mixedBanksExpected,res.toString());
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void parseCommercialBankStAX(){
        try {
            AbstractBuilder StAX = ParsersFactory.getParser("StAX");
            StAX.buildBankSets(commercialBankXmlSchema);
            Set<CommercialBank> commercialBanks = StAX.getCommercialBanks();
            StringBuilder res = new StringBuilder();
            for(CommercialBank c:commercialBanks){
                res.append(c.toString());
            }
            assertEquals(expectedCommB,res.toString());
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void parseNationalBankStAX(){
        try {
            AbstractBuilder StAX = ParsersFactory.getParser("StAX");
            StAX.buildBankSets(nationalBankXmlSchema);
            Set<NationalBank> nationalBanks = StAX.getNationalBanks();
            StringBuilder res = new StringBuilder();
            for(NationalBank c:nationalBanks){
                res.append(c.toString());
            }
            assertEquals(expectedNatB,res.toString());
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void parseBanksStAX(){
        try {
            AbstractBuilder StAX = ParsersFactory.getParser("StAX");
            StAX.buildBankSets(mixedBanksXmlSchema);
            Set<NationalBank> nationalBanks = StAX.getNationalBanks();
            Set<CommercialBank> commercialBanks = StAX.getCommercialBanks();
            StringBuilder res = new StringBuilder();
            for(CommercialBank c:commercialBanks){
                res.append(c.toString());
            }
            for(NationalBank c:nationalBanks){
                res.append(c.toString());
            }
            assertEquals(mixedBanksExpected,res.toString());
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void parseCommercialBankDOM(){
        try {
            AbstractBuilder DOM = ParsersFactory.getParser("DOM");
            DOM.buildBankSets(commercialBankXmlSchema);
            Set<CommercialBank> commercialBanks = DOM.getCommercialBanks();
            StringBuilder res = new StringBuilder();
            for(CommercialBank c:commercialBanks){
                res.append(c.toString());
            }
            assertEquals(expectedCommB,res.toString());
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void parseNationalBankDOM(){
        try {
            AbstractBuilder DOM = ParsersFactory.getParser("DOM");
            DOM.buildBankSets(nationalBankXmlSchema);
            Set<NationalBank> nationalBanks = DOM.getNationalBanks();
            StringBuilder res = new StringBuilder();
            for(NationalBank c:nationalBanks){
                res.append(c.toString());
            }
            assertEquals(expectedNatB,res.toString());
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void parseBanksDOM(){
        try {
            AbstractBuilder DOM = ParsersFactory.getParser("DOM");
            DOM.buildBankSets(mixedBanksXmlSchema);
            Set<NationalBank> nationalBanks = DOM.getNationalBanks();
            Set<CommercialBank> commercialBanks = DOM.getCommercialBanks();
            StringBuilder res = new StringBuilder();
            for(CommercialBank c:commercialBanks){
                res.append(c.toString());
            }
            for(NationalBank c:nationalBanks){
                res.append(c.toString());
            }
            assertEquals(mixedBanksExpected,res.toString());
        }
        catch (Exception e){
            fail();
        }
    }
}