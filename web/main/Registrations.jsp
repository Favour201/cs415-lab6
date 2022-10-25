

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="registration.js"></script>
        <script src="jquery-3.6.1.min.js"></script>
        <title>Registration Page</title>
    </head>
    <body>
        <h2>Welcome to the Registration Page</h2>
        <p>Please fill out the form below</p>
        
        <form name="attendeeregform" id="attendeeregform">
            <fieldset>
                <legend>Attendee Registration Information</legend>
                <p><input type="text" id="firstname" name="firstname" placeholder="First Name"></p>
                <p><input type="text" id="lastname" name="lastname" placeholder="Last Name"></p>
                <input type="text" id="displayname" name="displayname" placeholder="Display Name">
                <br>
                <br>
                <label for="sessionlist">Choose a Session: </label>
                <span id="sessionsmenu"></span>
                
                <p> <input type="button" value="Submit" onclick="Lab6A.onclickRegister();"></p>
            </fieldset>
        </form>
        <script type="text/javascript">
            $("#firstname").change(function(){
                var firstname = $("#firstname").val().trim();
                var lastname = $("#lastname").val().trim();
                $("#displayname").val(firstname + " " + lastname);
            });
            $("#lastname").change(function(){
                var firstname = $("#firstname").val().trim();
                var lastname = $("#lastname").val().trim();
                $("#displayname").val(firstname + " " + lastname);
            });
            Lab6A.getSessionsList();
        </script>
        <a href="Homepage.jsp">Click here to return to homepage</a>
    </body>
</html>
