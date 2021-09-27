package by.epamtc.jwd.controller.command;

import by.epamtc.jwd.exceptions.ParserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ParserException;
}
