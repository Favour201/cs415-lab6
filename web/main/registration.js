var Lab6A = (function(){
    var createMenuForSessions = function(response){
        
        var sessions = response.sessions;
        var dropDownMenu = document.getElementById("sessionsmenu");
        
        var select = document.createElement("select");
        select.name = "sessionid";
        select.id = "sessionid";
        select.style.backgroundColor = "red";
        
        for(var i = 0; i < sessions.length; i++) {
            var option = document.createElement("option");
            var description = sessions[i].description;
            
            var id = sessions[i].id;
            var listName = "(" + description + ")";
            
            if(id === 1)
                option.selected = true;
            
            option.value = id.toString();
            var listText = document.createTextNode(listName);
            option.appendChild(listText);
            select.appendChild(option);
            dropDownMenu.appendChild(select);
        }
        
    };
    var register = function(attendeeid) {
            $.ajax({
                url: "http://localhost:8180/Lab6A/main/Registrations",
                method: 'POST',
                data: {"sessionid": $("#sessionid").val(), "attendeeid": attendeeid},
                dataType: 'json',
                success: function(){
                    console.log("Successfully Registered Attendee");
                }
            
        });
    }; 
    var attendeeRegister = function() {
            $.ajax({
                url:"main/AttendeeServlet",
                method: 'POST',
                data: $("#attendeeform").serialize(),
                datatype: 'json',
                success: function(response){
                    register(response.id);
                   console.log("Successfully Added Attendee");
            }
            });
        };
    var editReg = function(){
            $.ajax({
                url: "http://localhost:8180/Lab6A/main/Registrations",
                method: 'PUT',
                data: $("#editRegistration").serialize(),
                datatype: 'json',
                success: function(){
                    console.log("Edit was successful!!");
                }
            });
        };
    
    var deleteA = function(){
            $.ajax({
                url: "http://localhost:8180/Lab6A/main/Registrations",
                method: 'DELETE',
                data: $("#deleteregistration").serialize(),
                datatype: 'json',
                success: function(){
                    console.log("Deletion was successful!!");
                }
            });
        };
    var editUser = function(){
            $.ajax({
                url: "main/AttendeeServelet",
                method: 'PUT',
                data: $("#attendeeEditForm").serialize(),
                datatype: 'json',
                success: function(){
                    console.log("Edit was successful!!");
                }
            });
        };
    return {
       
        getSessionsList: function(){
            $.ajax({
                url: "main/TrainingSessionServlet",
                method: 'GET',
                datatype: 'json',
                success: function(response){
                    createMenuForSessions(response);
                }
            });
        },
        onclickRegister: function(){
            attendeeRegister();
        },
        
        onclickEditReg: function(){
            editReg();
        },
        onclickDelete: function(){
            deleteA();
        },
        onclickEditUser: function(){
            editUser();
        }
        
        
    };
    
    
})();


