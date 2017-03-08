<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <spring:url value="/resources/css/bootstrap.css" var="bootStrap"></spring:url>
    <spring:url value="/resources/css/bootstrap.min.css" var="bootStrapMin"></spring:url>
    <spring:url value="/resources/css/font-awesome.min.css" var="fontAwesome"></spring:url>

    <spring:url value="/resources/js/jquery.js" var="jqueryJs"></spring:url>
    <spring:url value="/resources/js/bootstrap.min.js" var="boostrapMinJs"></spring:url>

    <link rel="stylesheet" type="text/css" href="${bootStrap}"/>
    <link rel="stylesheet" type="text/css" href="${bootStrapMin}"/>
    <link rel="stylesheet" type="text/css" href="${fontAwesome}"/>

    <title>TODO Page</title>


</head>
<body>
<h1>
    Add TODO
</h1>

<c:url var="addAction" value="/todo/add"></c:url>

<form:form action="${addAction}" commandName="todo">
    <table>
        <c:if test="${!empty todo.title}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="title">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td>
                <form:input path="title"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="description">
                    <spring:message text="Description"/>
                </form:label>
            </td>
            <td>
                <form:textarea path="description"/>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <c:if test="${!empty todo.title}">
                    <input type="submit"
                           value="<spring:message text="Edit TODO"/>"/>
                </c:if>
                <c:if test="${empty todo.title}">
                    <input type="submit"
                           value="<spring:message text="Add TODO"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<br>
<spring:url value="/todolist" var="listURL"/>
<display:table name="todolist" requestURI="${listURL}" pagesize="5" class="table" id="list">
    <display:column property="id" title="ID"/>
    <display:column property="title" title="Title"/>
    <display:column title="Description">
        <pre>${list.description}</pre>
    </display:column>
    <display:column title="Done">
        <a href="/mark-done/${list.id}">
            <button type="button" class="btn btn-default" aria-label="Edit">
                <c:choose>
                    <c:when test="${list.done}">
                        <span class="glyphicon glyphicon-check" aria-hidden="true"></span>
                    </c:when>
                    <c:otherwise>
                        <span class="glyphicon glyphicon-unchecked" aria-hidden="true"></span>
                    </c:otherwise>
                </c:choose>
            </button>
        </a>
    </display:column>
    <display:column><a href="/edit/${list.id}">
        <button type="button" class="btn btn-default" aria-label="Edit">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
        </button>
    </a>
        <a href="/remove/${list.id}">
            <button type="button" class="btn btn-default" aria-label="Edit">
                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
            </button>
        </a>
    </display:column>

</display:table>

</body>
</html>
