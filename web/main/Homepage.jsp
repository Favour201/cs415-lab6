<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="registration.js"></script>
        <title>Homepage</title>
    </head>
    <body>
         <nav>
            <h1>Welcome</h1>
            <p>Please click a link below to access your desired page</p>
            
                <li><a href="/Lab6A/main/Registrations.jsp">Registration Page</a></li>
                <li><a href="/Lab6A/main/Sessions.jsp">Sessions Page</a></li>
                <%
                    if (request.isUserInRole("administrator")){
                %>
                <li><a href="Attendees.jsp">Attendees Page</a></li>
                <%
                    }
                %>
                
                <p>
                    <input type="button" value="Logout" onclick="window.open('<%= request.getContentType()%>/main/logout.jsp','_self',false)"/>
                </p>
                
            
        </nav>

        
        <p>For more information about this API, see the instructions on Canvas.</p>
    </body>
</html>
