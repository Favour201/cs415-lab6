
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="jquery-3.6.1.min.js"></script>
        <script type="text/javascript" src="registration.js"></script>
        <title>Sessions Page</title>
    </head>
    <body>
        <h2>Welcome to the Sessions Page</h2>
        
        <form id="editRegistration" name="editRegistration">
            <fieldset>
                <legend>Edit A Registration</legend>
                <p>
                    <input name="s_attendeeid" id="s_attendeeid" type="number" placeholder="Enter ID Number"></p>
                <p>
                <input name="s_sessionid" id="s_sessionid" type="number" placeholder="Current Session #">
                </p>
                <p>
                <input name="newsessionid" id="newsessionid" type="number" placeholder="New Session #">
                </p>
                <p>
                    <input type="button" value="Edit Registration" onclick="Lab6A.onclickEditReg();";>
                </p>
            </fieldset>
        </form>
        
        <br>
        
        <form id="deleteregistration" name="deleteregistration">
            <fieldset>
                <legend>Delete a Registration</legend>
                
                <p>
                    <input name="deleteattendeeid" id="deleteattendeeid" type="number" placeholder="Enter ID number">
                </p>
                <p>
                    <input name="deletesessionid" id="deletesessionid" placeholder="Eneter Session #">
                </p>
                <p>
                    <input type="button" value="Delete" onclick="Lab6A.onclickDelete();">
                </p>
            </fieldset>
        </form>
        <a href="homepage.jsp">Click here to return to homepage</a>
    </body>
</html>
