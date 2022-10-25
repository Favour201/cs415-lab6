

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="jquery-3.6.1.min.js"></script>
        <script type="text/javascript" src="registration.js"></script>
        <title>Attendees Page</title>
    </head>
    <body>
        <h2>Welcome to the Attendees Page</h2>
        <form name="attendeeEditForm" id="attendeeEditForm">
            <fieldset>
                <legend>Edit User Profile</legend>
                <p><input name="attendeeid" id="attendeeid" type="number" placeholder="Your ID Number"></p>
                <p><input name="newfirstname" id="newfirstname" type="text" placeholder="New First Name"></p>
                <p><input name="newlastname" id="newlastname" type="text" placeholder="New Last Name"></p>
                <p><input name="newdisplayname" id="newdisplayname" type="text" placeholder="New Display Name"></p>
                <p><input type="button" value="Edit Attendee" onclick="Lab6A.onclickEditUser()"></p>
            </fieldset>
        </form>
        <a href="Homepage.jsp">Click here to return to the homepage</a>
        
        <script type="text/javascript">
            $("#newfirstname").change(function(){
                var firstname = $("#newfirstname").val().trim();
                var lastname = $("#newlastname").val().trim();
                $("#newdisplayname").val(firstname + " " + lastname);
            });
            $("#newlastname").change(function(){
                var firstname = $("#newfirstname").val().trim();
                var lastname = $("#newlastname").val().trim();
                $("#newdisplayname").val(firstname + " " + lastname);
            });
        </script>
    </body>
</html>
