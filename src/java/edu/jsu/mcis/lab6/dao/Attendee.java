
package edu.jsu.mcis.lab6.dao;

public class Attendee {
    
     private final Integer id;
    private final String firstname, lastname, displayname;
    
    public Attendee(Integer id, String firstname, String lastname, String displayname){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.displayname = displayname;
    }
    
    public Integer getId(){
        return id;
    }
    
    public String getFirstname(){
        return firstname;
    }
    
    public String getLastName(){
        return lastname;
    }
    
    public String getDisplayName(){
        return displayname;
    }
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("#").append(id).append(": ").append(displayname);
        return s.toString();
    }
    
}

    
   
