package final_gui_calculator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager {

	 public static List<String> getLastThreeEntries() {
	        List<String> lastThreeEntries = new ArrayList<>();
	        String sql = "SELECT * FROM calculation_history ORDER BY timestamp DESC LIMIT 3";
	        

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Retrieve the relevant columns from the ResultSet and format them
                String firstNumber = rs.getString("first_number");
                String operator = rs.getString("operator");
                String secondNumber = rs.getString("second_number");
                String result = rs.getString("result");
                String entry = String.format("%s %s %s = %s", firstNumber, operator, secondNumber, result);
                lastThreeEntries.add(entry);
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastThreeEntries;
    }
}
