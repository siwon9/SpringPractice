<%@ page contentType="text/html; charset=UTF-8" %>
<%
    pageContext.setAttribute("num", 10);
%>

${100+200}<br>
${num==10? "Number 10" : " Not Number 10"}