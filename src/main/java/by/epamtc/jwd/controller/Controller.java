package by.epamtc.jwd.controller;

import by.epamtc.jwd.controller.command.Command;
import by.epamtc.jwd.controller.command.CommandProvider;
import by.epamtc.jwd.exceptions.ParserException;
import by.epamtc.jwd.parsers.AbstractBuilder;
import by.epamtc.jwd.parsers.ParsersFactory;
import by.epamtc.jwd.validation.ExtensionChecker;

import java.io.*;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

public class Controller extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html;charset=UTF-8");

        System.out.println("GET");


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        Logger rootLogger = LogManager.getLogManager().getLogger("Root");

        response.setContentType("text/html;charset=UTF-8");
        System.out.println("POST");

        try {
            String action = request.getParameter("action");

            if(action != null){
                CommandProvider commandProvider = CommandProvider.getInstance();
                Command command = commandProvider.getCommand(action);
                command.execute(request, response);
            }
            else{
                response.sendRedirect("/");
            }

        }
        catch (ParserException | IOException e){
            rootLogger.log(Level.SEVERE,e.getMessage());
        }
    }

}