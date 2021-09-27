package by.epamtc.jwd.controller.command;

import by.epamtc.jwd.model.CommercialBank;
import by.epamtc.jwd.model.NationalBank;
import by.epamtc.jwd.parsers.AbstractBuilder;
import by.epamtc.jwd.parsers.ParsersFactory;
import by.epamtc.jwd.validation.ExtensionChecker;
import utils.FileRemover;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParseCommand implements Command{

    private static String xmlSchemaPath;
    private static String xsdSchemaPath;

    private void dataUploader(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
            Part[] parts = request.getParts().toArray(new Part[0]);
            HttpSession session = request.getSession();
            if (ExtensionChecker.isCorrectFile(parts[0], ".xml") &&
                    ExtensionChecker.isCorrectFile(parts[1], ".xsd")) {
                for (Part p : parts) {
                    String path = "C:/uploads/" + p.getSubmittedFileName();
                    if(path.contains(".xml")){
                        xmlSchemaPath = path;
                    }
                    else if(path.contains(".xsd")){
                        xsdSchemaPath = path;
                    }
                    FileWriter fileWriter = new FileWriter(new File(path));
                    InputStream is = p.getInputStream();
                    int info = 0;
                    while ((info = is.read()) != -1) {
                        fileWriter.write(info);
                    }
                    fileWriter.close();
                    is.close();
                }
                session.setAttribute("Incorrectextension", "false");
            }
            else {
                session.setAttribute("Incorrectextension", "true");
            }
        }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String parserType = request.getParameter("parserType");
        try {
            dataUploader(request, response);
            AbstractBuilder parser = ParsersFactory.getParser(parserType);
            parser.buildBankSets(xmlSchemaPath);
            FileRemover.removeFileByPath(xmlSchemaPath);
            FileRemover.removeFileByPath(xsdSchemaPath);
            for(CommercialBank cb:parser.getCommercialBanks()){
                System.out.println(cb);
            }
            for(NationalBank nb:parser.getNationalBanks()){
                System.out.println(nb);
            }
        }
        catch (Exception e){
            Logger.getLogger("root").log(Level.WARNING,"An error with parser occured",e);
        }
        finally {
            try {
                response.sendRedirect(request.getContextPath());
            }
            catch (IOException e){

            }
        }
    }
}
