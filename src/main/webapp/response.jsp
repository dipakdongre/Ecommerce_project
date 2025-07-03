<html>
<body>
<h2>Paytm Payment Response</h2>
<pre>
<%
    java.util.Enumeration<String> paramNames = request.getParameterNames();
    while (paramNames.hasMoreElements()) {
        String paramName = (String) paramNames.nextElement();
        out.println(paramName + " = " + request.getParameter(paramName) + "\\n");
    }
%>
</pre>
</body>
</html>
