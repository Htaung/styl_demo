<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title>Sort Number</title>
</head>
<body>
  <form method="POST" action="sort_number" >	
	 <h1>Sort Numbers</h1>
	 <hr>
	 <label>Enter Number Array</label>
	 <input type="text" name="numbers" value="${numbers}"/>
	
	<c:if test="${not empty sortedNumber}"> 
		 <label>Random Number</label>
		 <input type="text" name="sortedNumber" value="${sortedNumber}"/>
	</c:if>
		
	 <input type="submit" value="Submit">
  </form>
 </body>
 
 </html>
 
