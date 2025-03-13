package com.BasePackage;

import java.io.IOException;
import java.sql.*;

public class ExecuteStoredProcedure {

	// Method to execute the stored procedure and return DBMS_OUTPUT lines
    public static String callLoadAndValidateAccountsSP() throws IOException {
        Connection conn = null;
        CallableStatement callableStatement = null;
        CallableStatement enableDbmsOutput = null;
        CallableStatement getLineStmt = null;
        StringBuilder result = new StringBuilder();

        try {
            // Step 1: Use the existing database connection
            conn = Base_Class.OracleDBConnection();

            // Step 2: Enable DBMS_OUTPUT
            String enableOutputSQL = "BEGIN DBMS_OUTPUT.ENABLE(1000000); END;";
            enableDbmsOutput = conn.prepareCall(enableOutputSQL);
            enableDbmsOutput.execute();

            // Step 3: Execute the stored procedure
            String callSP = "{ CALL load_and_validate_accounts }";
            callableStatement = conn.prepareCall(callSP);
            callableStatement.execute();

            // Step 4: Fetch DBMS_OUTPUT using DBMS_OUTPUT.GET_LINE
            String getLineSQL = "BEGIN DBMS_OUTPUT.GET_LINE(?, ?); END;";
            getLineStmt = conn.prepareCall(getLineSQL);

            while (true) {
                getLineStmt.registerOutParameter(1, Types.VARCHAR); // Line content
                getLineStmt.registerOutParameter(2, Types.INTEGER); // Status
                getLineStmt.execute();

                String line = getLineStmt.getString(1);
                int status = getLineStmt.getInt(2);

                if (status != 0) { // No more lines to read
                    break;
                }
                if (line != null) {
                    result.append(line).append("\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result.append("Error: ").append(e.getMessage());
        } finally {
            // Step 5: Close resources
            try {
                if (getLineStmt != null) getLineStmt.close();
                if (callableStatement != null) callableStatement.close();
                if (enableDbmsOutput != null) enableDbmsOutput.close();
                // Do not close `conn` if it is managed by Base_Class
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString().trim();
    }
    
 // Generic method to execute any stored procedure
    public static String executeStoredProc(String storedProcName) throws IOException {
    	Connection conn = null;
        CallableStatement callableStatement = null;
        CallableStatement enableDbmsOutput = null;
        CallableStatement getLineStmt = null;
        StringBuilder result = new StringBuilder();
        String statusMessage = "";

        try {
            // Step 1: Use the existing database connection
            conn = Base_Class.OracleDBConnection();

            // Step 2: Enable DBMS_OUTPUT
            String enableOutputSQL = "BEGIN DBMS_OUTPUT.ENABLE(1000000); END;";
            enableDbmsOutput = conn.prepareCall(enableOutputSQL);
            enableDbmsOutput.execute();

            // Step 3: Modify the call to the stored procedure to include an OUT parameter for the message
            String callSP = "{ CALL " + storedProcName + "(?)}";  // Assuming the stored proc has an OUT parameter
            callableStatement = conn.prepareCall(callSP);
            
            // Register the OUT parameter to capture the status message
            callableStatement.registerOutParameter(1, Types.VARCHAR);  // OUT parameter for message

            callableStatement.execute();

            // Get the success/failure message from the OUT parameter
            statusMessage = callableStatement.getString(1);  // Fetch the OUT parameter value

            // Step 4: Fetch DBMS_OUTPUT using DBMS_OUTPUT.GET_LINE if needed (optional)
            String getLineSQL = "BEGIN DBMS_OUTPUT.GET_LINE(?, ?); END;";
            getLineStmt = conn.prepareCall(getLineSQL);

            while (true) {
                getLineStmt.registerOutParameter(1, Types.VARCHAR); // Line content
                getLineStmt.registerOutParameter(2, Types.INTEGER); // Status
                getLineStmt.execute();

                String line = getLineStmt.getString(1);
                int status = getLineStmt.getInt(2);

                if (status != 0) { // No more lines to read
                    break;
                }
                if (line != null) {
                    result.append(line).append("\n");
                }
            }

            // Check if the status message from the stored procedure is empty or null
            if (statusMessage == null || statusMessage.isEmpty()) {
                // Append a default success message if none is returned
                statusMessage = "Stored procedure executed successfully, but no message was returned.";
            }

            // Append the status message (from the OUT parameter) to the result if no DBMS_OUTPUT lines are found
            if (result.length() == 0) {
                result.append(statusMessage);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            result.append("Error: ").append(e.getMessage());
        } finally {
            // Step 5: Close resources
            try {
                if (getLineStmt != null) getLineStmt.close();
                if (callableStatement != null) callableStatement.close();
                if (enableDbmsOutput != null) enableDbmsOutput.close();
                // Do not close `conn` if it is managed by Base_Class
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return result.toString().trim();
    }
    
    public static String executeStoredProce(String storedProcName) throws IOException {
        Connection conn = null;
        CallableStatement callableStatement = null;
        CallableStatement enableDbmsOutput = null;
        CallableStatement getLineStmt = null;
        StringBuilder result = new StringBuilder();
        String statusMessage = "";

        try {
            // Step 1: Use the existing database connection
            conn = Base_Class.OracleDBConnection();

            // Step 2: Enable DBMS_OUTPUT
            String enableOutputSQL = "BEGIN DBMS_OUTPUT.ENABLE(1000000); END;";
            enableDbmsOutput = conn.prepareCall(enableOutputSQL);
            enableDbmsOutput.execute();

            // Step 3: Define and execute the dynamic stored procedure based on the name passed
            String callSP = "{ CALL " + storedProcName + " }"; // Assuming no IN/OUT parameters
            callableStatement = conn.prepareCall(callSP);
            callableStatement.execute();

            // Step 4: Fetch DBMS_OUTPUT using DBMS_OUTPUT.GET_LINE
            String getLineSQL = "BEGIN DBMS_OUTPUT.GET_LINE(?, ?); END;";
            getLineStmt = conn.prepareCall(getLineSQL);

            while (true) {
                getLineStmt.registerOutParameter(1, Types.VARCHAR); // Line content
                getLineStmt.registerOutParameter(2, Types.INTEGER); // Status
                getLineStmt.execute();

                String line = getLineStmt.getString(1);
                int status = getLineStmt.getInt(2);

                if (status != 0) { // No more lines to read
                    break;
                }
                if (line != null) {
                    result.append(line).append("\n");
                }
            }

            // Step 5: Return the result (DBMS_OUTPUT lines or success message)
            if (result.length() == 0) {
                statusMessage = "Stored procedure executed successfully, but no DBMS_OUTPUT lines were captured.";
                result.append(statusMessage);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            result.append("Error: ").append(e.getMessage());
        } finally {
            // Step 6: Close resources
            try {
                if (getLineStmt != null) getLineStmt.close();
                if (callableStatement != null) callableStatement.close();
                if (enableDbmsOutput != null) enableDbmsOutput.close();
                // Do not close `conn` if it is managed by Base_Class
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return result.toString().trim();
    }

    public static String executeInsertEmployeeSP(String userId) throws IOException { 
        Connection conn = null;
        CallableStatement callableStatement = null;
        String statusMessage = "";

        try {
            // Step 1: Get database connection
            conn = Base_Class.OracleDBConnection(); 

            // Step 2: Prepare the stored procedure call
            String callSP = "{ CALL SYSTEM.SP_INSERT_EMPLOYEE(?, ?) }";
            //String callSP = "{ CALL SP_INSERT_EMPLOYEE(?, ?) }";  // 1 IN param, 1 OUT param
            callableStatement = conn.prepareCall(callSP);

            // Set IN parameter (User ID)
            callableStatement.setString(1, userId);

            // Register OUT parameter (p_message)
            callableStatement.registerOutParameter(2, Types.VARCHAR);

            // Step 3: Execute stored procedure
            callableStatement.execute();

            // Step 4: Retrieve the OUT parameter value (status message)
            statusMessage = callableStatement.getString(2);

        } catch (SQLException e) {
            e.printStackTrace();
            statusMessage = "Error: " + e.getMessage();
        } finally {
            // Step 5: Close resources
            try {
                if (callableStatement != null) callableStatement.close();
                // Do not close `conn` if managed by Base_Class
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return statusMessage;
    }    
    public static void main(String[] args) throws IOException {
        // Example usage
    	
    	String userId = "IBU0001196";  // Provide a valid user ID
        String result = executeInsertEmployeeSP(userId);
        System.out.println(result);

    }

}
