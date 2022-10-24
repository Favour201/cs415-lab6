

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registration Page</title>
    </head>
    <body>
        <h2>Welcome to the Registration Page</h2>
        <p>Please fill out the form below</p>
        
        <form>
            <fieldset>
                <legend>Registration Information</legend>
                <input type="text" id="firstname" name="firstname" placeholder="First Name">
                <input type="text" id="lastname" name="lastname" placeholder="Last Name">
                <input type="text" id="displayname" name="displayname" placeholder="Display Name">
                <br>
                <br>
                <label for="sessionlist">Choose a Session: </label>
                <select name="sessionlist" id="sessionlist">
                    <option value="Session1">Session 1 (TR 9:15am - 10:45am)</option>
                    <option value="Session2">Session 2 (TR 12:45 pm - 2:15 pm)</option>
                    <option value="Session3">Session 3 (MWF 11:15 am - 12:15 pm)</option>
                    <option value="Session4">Session 4 (MWF 1:45 pm - 2:45 pm)</option>
                </select>
            </fieldset>
        </form>
        <a href="Homepage.jsp">Click here to return to homepage</a>
    </body>
</html>
