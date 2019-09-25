<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<style>
table, th, td {
  border: 1px solid black;
  padding: 5px;
}
table {
  border-spacing: 15px;
}</style>
<title>Insert title here</title>
</head>
<body>
<c:out value="${uname}"/>
<c:forEach items= "${list}" var="item">
<table>
  
    <tr>
      <td><c:out value= "From : ${item.startPoint}"/></td>
      <td><c:out value= "To: ${item.endPoint}"/></td>
    </tr>
    <tr>
      <td><c:out value= "Departure: ${item.departure}"/></td>
      <td><c:out value= "Arrival: ${item.arrival}"/></td>
    </tr>
    <tr>
      <td><c:out value= "Free seats: ${item.places} seats"/></td>
      <td><c:out value= "Price: ${item.price} $"/></td>
      <td><input type="submit" value="Buy"></td>
    </tr>
    <tr><tr><tr><tr><tr><tr>
  
</table>
</c:forEach>

<form action = "TicketApp/login" method = "POST">
	<input type = "submit" value = "Logout">
</form>
</body>
</html>