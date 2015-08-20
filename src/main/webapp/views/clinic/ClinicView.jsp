<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Clinic</title>
    <link href="../../css/clinicview.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="header">
    <div class="logo">
        <img src="../../image/clinic11.jpg" alt="Логотип" name="logo"/>
    </div>
    <div class="descr">
        <h4>Клиника домашних животных</h4>
    </div>
</div>
<div class="sidebar1">
    <div class="add_form">
        <form action="${pageContext.servletContext.contextPath}/add" method='post'>
            <h2>Client</h2>
            client's name: <input type='text' name='clientName'>
            <br/>
            </br>
            city: <input type='text' name='clientCity'>
            </br>
            </br>
            street: <input type='text' name='clientStreet'>
            </br>
            </br>
            house: <input type='text' name='clientHouse'>
            </br>
            </br>
            apartment: <input type='text' name='clientApartment'>
            </br>
            </br>
            age: <input type='text' name='clientAge'>
            </br>
            </br>
            <select name="sex">
                <option value="male">male</option>
                <option value="female">female</option>
            </select>
            </br>
            <h2>Pet</h2>
            pet's nick : <input type='text' name='petName'>
            </br>
            </br>
            age: <input type='text' name='petAge'>
            </br>
            </br>
            <select name="pets">
                <option value="cat">Cat</option>
                <option value="dog">Dog</option>
                <option value="parrot">Parrot</option>
            </select>
            <select name="sexPet">
                <option value="male">male</option>
                <option value="female">female</option>
            </select>
            </br>
            </br>
            <input type='submit' value='Submit'>
            </br>
        </form>
    </div>
    <div class="edit_form">

    </div>
    <div class="del_form">
        <form action="${pageContext.servletContext.contextPath}/del" method='post'>
            <h3>Delete client</h3>
            Client's nick for delete:
            </br>
            <input type='text' name='clientNameDel'>
            <input type='submit' value='Delete'>
        </form>
    </div>
    <div class="search_form">
        <form action="${pageContext.servletContext.contextPath}/search" method='post'>
            Search pet by client's nick:
            </br>
            <input type='text' name='search'>
            <input type='submit' value='Search'>
        </form>
    </div>
</div>
<div class="content">
    <h2>Clients:</h2>
    <table border="1">
        <tr>
            <td>Id</td>
            <td>Client's nick</td>
            <td>Pet's nick</td>
            <td>Pet's type</td>
            <td>Edit client</td>
        </tr>
        <c:forEach items="${clinic[values]}" var="client" varStatus="status">
            <tr valign="top">
                <td>${client.id}</td>
                <td>${client.getName()}</td>
                <td>${client.getPet().getNick()}</td>
                <td>${client.getPet().getClass()}</td>
                <td><a href="${pageContext.servletContext.contextPath}/edit?id=${client.getId()}">edit</a></td>
            </tr>
        </c:forEach>
    </table>
    </table>
</div>
<div class="content_search">
    <h2>Founded clients:</h2>
    <table border="1">
        <tr>
            <td>Id</td>
            <td>Client's nick</td>
            <td>Pet's nick</td>
            <td>Pet's type</td>

        </tr>
        <c:forEach items="${clients}" var="client" varStatus="status">
            <tr valign="top">
                <td>${client.getId()}</td>
                <td>${client.getName()}</td>
                <td>${client.getPet().getName()}</td>
                <td>${client.getPet().getClass().getSimpleName()}</td>

            </tr>
        </c:forEach>
    </table>
    </table>
</div>

<!-- end .container -->


</body>
</html>
