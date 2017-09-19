import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public DoctorDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public String loginCheck(Login obj) throws SQLException {

		String sql = "SELECT * FROM patient WHERE name = ? AND pwd=?";
		String uname = obj.getUname();
		String pwd = obj.getPwd();
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, uname);
		statement.setString(2, pwd);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			resultSet.close();
			statement.close();
			return "true";
		} else {
			resultSet.close();
			statement.close();
			return "false";
		}

	}

	public boolean insertDoctor(Doctor doctor) throws SQLException {
		String sql = "INSERT INTO doctor (name, designation, dept) VALUES (?, ?, ?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, doctor.getname());
		statement.setString(2, doctor.getdesignation());
		statement.setString(3, doctor.getdept());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<Doctor> listAllDoctors() throws SQLException {
		List<Doctor> listDoctor = new ArrayList<>();

		String sql = "SELECT * FROM doctor";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);     	

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String designation = resultSet.getString("designation");
			String dept = resultSet.getString("dept");

			Doctor doctor = new Doctor(id, name, designation, dept);
			listDoctor.add(doctor);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listDoctor;
	}
	
	public List<DocPat> llistAllDoctors() throws SQLException {
		List<DocPat> llistDocPat = new ArrayList<>();

		String sql = "select a.Name,b.patientname from doctor as a right join docpat as b on a.id=b.id";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);     	

		while (resultSet.next()) {
			String doc_name = resultSet.getString("Name");
			String name = resultSet.getString("patientname");
			
			DocPat doctor2 = new DocPat(doc_name , name);
			llistDocPat.add(doctor2);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return llistDocPat;
	}
	
	
	public boolean insertDoctorPatient(DocPat doctor) throws SQLException {
		String sql = "INSERT INTO docpat (id , patientname) VALUES (?, ?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, doctor.getId());
		statement.setString(2, doctor.getName());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	/*public List<Doctor> listAllDoctors() throws SQLException {
		List<Doctor> listDoctor = new ArrayList<>();

		String sql = "SELECT * FROM doctor";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);     	

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String designation = resultSet.getString("designation");
			String dept = resultSet.getString("dept");

			Doctor doctor = new Doctor(id, name, designation, dept);
			listDoctor.add(doctor);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listDoctor;
	}
	
	*/
	/*public List<Docpat> displayOutput() throws SQLException{
       List<DocPat> displayOutput = new ArrayList<>();
       String sql = "Select a.Name,b.patientname from doctor as a right join docpat as b on a.id=b.id";
       connect();
       
       Statement statement = jdbcConncetion.createStatement();
       ResultSet resultSet = statement.executeQuery(sql);
       
       while(resultSet.next()) {
    	   int id = resultSet.getId("docpat_id");
    	   int docid = resultSet.getId("id");
    	   String name = resultSet.getString("patientname");
    	   
    	   DocPat doctor2 = new DocPat(docpat_id,id,patientname);
			displayOutput.add(doctor2);
    	   
       }
	}*/
	public List<Doctor> selectDoctor() throws SQLException {
		List<Doctor> selectDoctor = new ArrayList<>();

		String sql = "SELECT * FROM doctor";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);     	

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String designation = resultSet.getString("designation");
			String dept = resultSet.getString("dept");

			Doctor doctor1 = new Doctor(id, name, designation, dept);
			selectDoctor.add(doctor1);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return selectDoctor;
	}
	
	
	
	

	public boolean deleteDoctor(Doctor doctor) throws SQLException {
		String sql = "DELETE FROM doctor where id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, doctor.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean updateDoctor(Doctor doctor) throws SQLException {
		String sql = "UPDATE doctor SET name = ?, designation = ?, dept = ?";
		sql += " WHERE id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, doctor.getname());
		statement.setString(2, doctor.getdesignation());
		statement.setString(3, doctor.getdept());
		statement.setInt(4, doctor.getId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public Doctor getDoctor(int id) throws SQLException {
		Doctor doctor = null;
		String sql = "SELECT * FROM doctor WHERE id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String name = resultSet.getString("name");
			String designation = resultSet.getString("designation");
			String dept = resultSet.getString("dept");

			doctor = new Doctor(id, name, designation, dept);
		}

		resultSet.close();
		statement.close();

		return doctor;
	}

}
