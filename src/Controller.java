import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Controller extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;
 
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        doctorDAO = new DoctorDAO(jdbcURL, jdbcUsername, jdbcPassword);
        patientDAO = new PatientDAO(jdbcURL,jdbcUsername,jdbcPassword);
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
 
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertDoctor(request, response);
                break;
            case "/delete":
                deleteDoctor(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateDoctor(request, response);
                break;
            case "/add":
            	addNewForm(request, response);
            	break;
            case "/enter":
            	enterPatient(request,response);
            	break;
            case "/remove":
            	removePatient(request,response);
            	break;
            case "/show" :
            	showPatient(request,response);
            	break;
            case "/modify":
            	modifyPatient(request,response);
            	break;
            case "/login":
            	loginValidate(request,response);
            	break;
            case "/test":
            	test(request,response);
            case "/select":
            	selectDoctor(request,response);
            	break;
            case "/list":
            	 listDoctor(request, response);
                 break;
            case "/map":
            	insertDoctorPatient(request, response);
            	break;
            case "/llist":
            	llistDocPat(request, response);
            	break;
            default:
            	response.sendRedirect("index.jsp");
            	break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
 private void selectDoctor(HttpServletRequest request, HttpServletResponse response)
           throws SQLException ,IOException , ServletException{
	 List<Doctor> selectDoctor = doctorDAO.selectDoctor();
	 System.out.println("111111111111"+selectDoctor);
     request.setAttribute("selectDoctor", selectDoctor);
	 RequestDispatcher dispatcher = request.getRequestDispatcher("SelectDoctor.jsp");
		dispatcher.forward(request, response);
	}
 


private void showPatient(HttpServletRequest request, HttpServletResponse response)
		 throws SQLException, IOException, ServletException{
	 List<Patient> showPatient = PatientDAO.showAllPatients();
	 
	 /*Iterator itr = showPatient.iterator();
	 while(itr.hasNext()){
		 Patient p = (Patient)itr.next();
		 System.out.println(p.getname());
	 }*/
	 
     request.setAttribute("showPatient", showPatient);
     RequestDispatcher dispatcher = request.getRequestDispatcher("PatientList.jsp");
     dispatcher.forward(request, response);
		
		
	}
 private void addNewForm(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     RequestDispatcher dispatcher = request.getRequestDispatcher("PatientForm.jsp");
     dispatcher.forward(request, response);
 }
 private void test(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
     dispatcher.forward(request, response);
 }
/*
          super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("user");
			String password = request.getParameter("password");
			String sql = "insert into patient(name,password) values(?,?)";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitaldb", "root", "");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2,password);
			ps.executeUpdate();
			PrintWriter out = response.getWriter();
			out.println("Succesfully Registered"); 
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	} 
      
      
      
      
   
      
  */
    

private void removePatient(HttpServletRequest request, HttpServletResponse response) 
	throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Patient patient = new Patient(id);
        PatientDAO.removePatient(patient);
        response.sendRedirect("show");

		
	}

private void enterPatient(HttpServletRequest request, HttpServletResponse response)  throws SQLException, IOException{
	String name = request.getParameter("name");
    int age  = Integer.parseInt(request.getParameter("age"));
    String pwd = request.getParameter("password");
    System.out.println(pwd);
    Patient newPatient = new Patient(name,age,pwd);
    PatientDAO.enterPatient(newPatient);
    response.sendRedirect("show");
		
	}

private void modifyPatient(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    int age = Integer.parseInt(request.getParameter("age"));
    String pwd = request.getParameter("password");

    Patient patient = new Patient(id, name, age , pwd);
    PatientDAO.modifyPatient(patient);
    response.sendRedirect("show");
}

	private void loginValidate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		 HttpSession session = request.getSession(true);
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		System.out.println(pwd);
		Login obj=new Login(uname,pwd);
		if(uname.equals("admin") && pwd.equals("12345")){
			response.sendRedirect("DoctorList.jsp");
		}
		else{
		System.out.println("obj"+obj.getUname());
       String result= PatientDAO.loginCheck(obj);
   	System.out.println("result"+result);
        request.setAttribute("result", result);
        PrintWriter out = response.getWriter();
        if(result.equals("true")){
        	System.out.println("result  if("+result);
			session.setAttribute("id",obj.getUname());
            response.sendRedirect("show");
        }
         
        if(result.equals("false")){
        	out.println("Authentication failed");
           System.out.println("Failure");
        }
      
    }
	}
	
/* private void loginAdmin(HttpServletRequest request,HttpServletResponse response)
    throws SQLException,IOException,ServletException{
     
    String uname= "Admin";
    String pwd= "12345";
       // LoginUser obj=new LoginUser(acc_no,password);
        if(pwd.equals("12345")){
    //out.print("Welcome", uname);
    HttpSession session=request.getSession();
    //session.setAttribute("admin",admin);
     response.sendRedirect("list");
   
    }
    else{
    //out.print("Sorry, username or password error!");
    request.getRequestDispatcher("login.jsp").include(request, response);
    }
    }
 */
 
	private void logout(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
	
	//out.println("thanq you!!, Your session was destroyed successfully!!");
	HttpSession session = request.getSession(false);
	// session.setAttribute("user", null);
	session.removeAttribute("email");
	 response.sendRedirect("index.jsp");
	} 


    private void listDoctor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Doctor> listDoctor = doctorDAO.listAllDoctors();
        request.setAttribute("listDoctor", listDoctor);
        RequestDispatcher dispatcher = request.getRequestDispatcher("DoctorList.jsp");
        dispatcher.forward(request, response);
    }
    private void llistDocPat(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<DocPat> llistDocPat = doctorDAO.llistAllDoctors();
        request.setAttribute("llistDocPat", llistDocPat);
        RequestDispatcher dispatcher = request.getRequestDispatcher("FinalOutput.jsp");
        dispatcher.forward(request, response);
    }
    
    
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("DoctorForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Doctor existingDoctor = doctorDAO.getDoctor(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("DoctorForm.jsp");
        request.setAttribute("doctor", existingDoctor);
        dispatcher.forward(request, response);
 
    }
 
    private void insertDoctor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String designation = request.getParameter("designation");
        String dept = request.getParameter("dept");
     
 
        Doctor newDoctor = new Doctor(name, designation, dept);
        doctorDAO.insertDoctor(newDoctor);
        response.sendRedirect("list");
    }
    private void insertDoctorPatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int doctorid =Integer.parseInt(request.getParameter("id"));
        HttpSession session=request.getSession();
        String pat_name=(String)session.getAttribute("id");
     System.out.println("11111"+pat_name);
 
        DocPat newDoctor = new DocPat(doctorid,pat_name);
        doctorDAO.insertDoctorPatient(newDoctor);
        List<DocPat> llistDocPat = doctorDAO.llistAllDoctors();
        request.setAttribute("llistDocPat", llistDocPat);
        RequestDispatcher dispatcher = request.getRequestDispatcher("FinalOutput.jsp");
        try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void updateDoctor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String designation = request.getParameter("designation");
        String dept = request.getParameter("dept"); 
 
        Doctor doctor = new Doctor(id, name, designation, dept);
        doctorDAO.updateDoctor(doctor);
        response.sendRedirect("list");
    }
 
    private void deleteDoctor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Doctor doctor = new Doctor(id);
        doctorDAO.deleteDoctor(doctor);
        response.sendRedirect("list");
 
    }
}

