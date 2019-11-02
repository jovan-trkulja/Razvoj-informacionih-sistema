<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<c:choose>
            <c:when test="${saveFailed == true}">
				Greska prilikom cuvanja glumca. <br>
            </c:when>
            <c:otherwise>
				Glumac je uspesno sacuvan u bazu. ID glumca je ${glumac.ime}<br>
            </c:otherwise>
    </c:choose>
    
    <c:choose>
            <c:when test="${nijeSacuvano == true}">
				Greska prilikom cuvanja uloge <br>
            </c:when>
            <c:otherwise>
				Uloga je uspesno sacuvana. ID uloge je ${uloga.idUloga}  <br>
            </c:otherwise>
    </c:choose>
    
    <form action="/PozoristeWeb/PredstavaGlumacS" method="post">
    
    	<select name="predstave">
    	
    		<c:forEach items="${predstave}" var="p">
    			
    			<option value="${p.idPredstava}">${p.naziv}</option> <br>
    			
    		</c:forEach>
    	
    	</select> <br><br>
    	
    	Unesite ulogu glumca: <input type="text" name="uloga"> <br><br>
    	
    	<input type="submit" value="Sacuvaj">
    
    </form>
	
</body>
</html>