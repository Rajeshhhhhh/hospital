public class Patient{
	protected int id;
    protected String name;
    protected int age;
    protected String pwd;
    
 
    public Patient() {
    }
 
    public Patient(int id) {
        this.id = id;
    }
 
    public Patient(int id, String name, int age, String pwd) {
        this(name,age,pwd);
        this.id = id;
    }
     
    public Patient(String name, int age,String pwd) {
        this.name = name;
        this.age = age;
        this.pwd = pwd;
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
 
    public int getage() {
        return age;
    }
 
    public void setage(int age) {
        this.age = age;
    }
    
    public String getpwd(){
    	return pwd;
    }
    public void setpwd(String pwd){
         this.pwd = pwd;
    }
}
