package jdbctests;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        String dbUrl = "jdbc:oracle:thin:@52.207.61.129:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";


        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //Create Statement Object
        Statement statement = connection.createStatement();

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("Select * from regions");

        //move pointer to first row
        resultSet.next(); //0 baslamak noktasindan seni kurtarir ve bir sonraki adima atar boylece birinci siradan baslarsin

        //getting information with column name
        System.out.println(resultSet.getString("region_name"));

        //getting information with column index(starts From 1)
        System.out.println(resultSet.getString(2));

        System.out.println(resultSet.getString(1)+ " - " + resultSet.getString("region_name"));

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+ " -" +resultSet.getString("region_name"));
        }
        //close all connections
        resultSet.close();
        statement.close();
        connection.close();





    }
}
