package by.epamtc.jwd.parsers;

public enum ParserType {
    SAX("sax"),
    StAX("stax"),
    DOM("dom");

    private String value;
    private ParserType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
