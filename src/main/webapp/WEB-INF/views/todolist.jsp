<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
    <spring:url value = "/todolist" var = "listURL" />
    <display:table name = "todolist" requestURI = "${listURL}" pagesize = "5" >
        <display:column property = "id" title = "ID" />
        <display:column property = "title" title = "Title" />
        <display:column property = "description" title = "Description" />
        <display:column property = "isDone" title = "Done" />
    </display:table>

$END$
</body>
</html>
