<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parse your XML</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
</head>
<body>
<div class = "container">
    <form action="/XMLParser/parser" method="post" enctype="multipart/form-data">
        <div>
            <label for="uploadXML">Select a XML file</label>
            <input type="file" id="uploadXML" value="Select" name="uploadXML" >
        </div>
        <div>
            <label for="uploadXSD">Select a XSD file</label>
            <input type="file" id="uploadXSD" value="Select" name="uploadXSD">
        </div>
        <div>
            <input type = "radio"
                   name = "parserType" value="DOM">DOM

            <input type = "radio" id = "SAXParser"
                   name = "parserType" value="SAX">SAX

            <input type = "radio" id = "StAXParser"
                   name = "parserType" value="StAX">StAX

        </div>
        <div>
            <button type="submit" class="btn btn-warning btn-group-sm">Upload</button>
        </div>
            <input type="hidden" name="action" value="parse">
    </form>
    <c:if test="${sessionScope.get('Incorrectextension') eq 'true'}" >
        <c:out value="Incorrect file format. Follow the rules printed above"/>
        <c:set value = 'false' scope="session" var="Incorrectextension"/>
    </c:if>
</div>
</body>
</html>
