import java.util.*;
public class Doctor{
	protected int id;
    protected String name;
    protected String designation;
    protected String dept;
 
    public Doctor() {
    }
 
    public Doctor(int id) {
        this.id = id;
    }
 
    public Doctor(int id, String name, String designation, String dept) {
        this(name,designation,dept);
        this.id = id;
    }
     
    public Doctor(String name, String designation, String dept) {
        this.name = name;
        this.designation = designation;
        this.dept = dept;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getname() {
        return name;
    }
 
    public void setname(String name) {
        this.name = name;
    }
 
    public String getdesignation() {
        return designation;
    }
 
    public void setdesignation(String designation) {
        this.designation = designation;
    }
 
    public String getdept() {
        return dept;
    }
 
    public void setdept(String dept) {
        this.dept = dept;
    }

}
