package by.epamtc.jwd.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoSuchCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //redirect to 404 page
    }
}
