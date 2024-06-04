package final_gui_calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
	private static final String CONFIG_FILE = "/final_gui_calculator/config.properties";

		private static String url;
		private static String user;
		private static String password;
		
		static {
			try (InputStream input= DatabaseManager.class.getResourceAsStream(CONFIG_FILE)) {
				Properties prop = new Properties();
				if (input != null) {
		            prop.load(input);
		            url = prop.getProperty("db.url");
		            user = prop.getProperty("db.user");
		            password = prop.getProperty("db.password");
		        } else {
		            System.err.println("Unable to load config.properties file. Input stream is null.");
		        }
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	    public static void initializeDatabase() {
	        try (Connection conn = DriverManager.getConnection(url, user, password)) {
	            executeScript(conn, "gui_db.sql");
	            System.out.println("Database initialized successfully.");
	        } catch (SQLException | IOException e) {
	            e.printStackTrace();
	        }
	    }
	        public static void saveCalculation(String firstNumber, String operator, String secondNumber, String result) {
	            String sql = "INSERT INTO calculation_history (first_number, operator, second_number, result) VALUES (?, ?, ?, ?)";

	            try (Connection conn = DriverManager.getConnection(url, user, password);
	                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
	                pstmt.setString(1, firstNumber);
	                pstmt.setString(2, operator);
	                pstmt.setString(3, secondNumber);
	                pstmt.setString(4, result);
	                pstmt.executeUpdate();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        private static void executeScript(Connection conn, String scriptPath) throws SQLException, IOException {
	            try (BufferedReader reader = new BufferedReader(new FileReader(scriptPath))) {
	                PreparedStatement stmt = conn.prepareStatement("");
	                String line;
	                StringBuilder sb = new StringBuilder();
	                while ((line = reader.readLine()) != null) {
	                    sb.append(line);
	                    if (line.trim().endsWith(";")) {
	                        stmt.addBatch(sb.toString());
	                        sb.setLength(0);
	                    }
	                }
	                stmt.executeBatch();
	            }
	        }

	        public static void main(String[] args) {
	            initializeDatabase();
	            saveCalculation("10", "+", "5", "15");
	        }
}
