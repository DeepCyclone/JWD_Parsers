package by.epamtc.jwd.parsers;

import by.epamtc.jwd.exceptions.NoSuchParserException;
import by.epamtc.jwd.exceptions.ParserException;
import by.epamtc.jwd.parsers.dom.DOMBuilder;
import by.epamtc.jwd.parsers.sax.SAXBuilder;
import by.epamtc.jwd.parsers.stax.STaXBuilder;

import java.util.Locale;

public class ParsersFactory {
    public static AbstractBuilder getParser(String parserName) throws NoSuchParserException, ParserException {
        String name = parserName.toUpperCase(Locale.ROOT);
        AbstractBuilder abstractBuilder;
        switch (name){
            case "DOM":abstractBuilder =  new DOMBuilder();
                break;
            case "STAX":abstractBuilder =  new STaXBuilder();
                break;
            case "SAX":abstractBuilder =  new SAXBuilder();
                break;
            default:throw new NoSuchParserException();
        }
        return abstractBuilder;
    }
}
