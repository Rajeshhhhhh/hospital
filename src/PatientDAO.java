import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
	private static String jdbcURL;
	private static String jdbcUsername;
	private static String jdbcPassword;
	private static Connection jdbcConnection;

	public PatientDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected static void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected static void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public static String loginCheck(Login obj) throws SQLException {

		String sql = "SELECT * FROM patient WHERE Name = ? AND pwd=?";
		String uname= obj.getUname();
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

	public static boolean enterPatient(Patient patient) throws SQLException {
		String sql = "INSERT INTO patient (name, age, pwd) VALUES (?, ?,?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		System.out.println("passwor"+patient.getpwd());
		statement.setString(1, patient.getname());
		statement.setInt(2, patient.getage());
		statement.setString(3, patient.getpwd());

		boolean infoInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return infoInserted;
	}

	public static List<Patient> showAllPatients() throws SQLException {
		List<Patient> showPatient = new ArrayList<>();

		String sql = "SELECT * FROM patient";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);     	

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int age = resultSet.getInt("age");
			String pwd = resultSet.getString("pwd");
			Patient patient = new Patient(id, name, age , pwd);
			showPatient.add(patient);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return showPatient;
	}

	public static boolean removePatient(Patient patient) throws SQLException {
		String sql = "DELETE FROM patient where id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, patient.getId());

		boolean deletedRow = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return deletedRow;
	}

	public static boolean modifyPatient(Patient patient) throws SQLException {
		String sql = "UPDATE patient SET name = ?, age = ?, pwd = ?";
		sql += " WHERE id = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, patient.getname());
		statement.setInt(2, patient.getage());
		statement.setString(3, patient.getpwd());
		statement.setInt(4, patient.getId());

		boolean updatedRow = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return updatedRow;
	}

	public Patient getPatient(int id) throws SQLException {
		Patient patient = null;
		String sql = "SELECT * FROM patient WHERE id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String name = resultSet.getString("name");
			int age = resultSet.getInt("age");
			String pwd = resultSet.getString("pwd");

			patient = new Patient(id, name,age,pwd);
		}

		resultSet.close();
		statement.close();

		return patient;
	}

}
