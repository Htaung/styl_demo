<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title>Random Number</title>
</head>
<body>
  <form method="POST" action="random_number" >	
	 <h1>Random Number</h1>
	 <hr>
	 <label>Enter Maximum Number</label>
	 <input type="text" name="maxNumber" value="${maxNumber}"/>
	
	<c:if test="${not empty randomNumber}"> 
		 <label>Random Number</label>
		 <input type="text" name="randomNumber" value="${randomNumber}"/>
	</c:if>
		
	 <input type="submit" value="Submit">
  </form>
 </body>
 
 </html>
 
