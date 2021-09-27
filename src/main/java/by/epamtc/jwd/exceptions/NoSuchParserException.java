package by.epamtc.jwd.exceptions;

public class NoSuchParserException extends Exception{
    public NoSuchParserException() {
    }

    public NoSuchParserException(String message) {
        super(message);
    }

    public NoSuchParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchParserException(Throwable cause) {
        super(cause);
    }

    public NoSuchParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
