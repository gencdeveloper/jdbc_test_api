package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {


    String dbUrl = "jdbc:oracle:thin:@52.207.61.129:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException{

        //create connection
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //Create Statement Object and argument //ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY
        //allow us to navigate up and down in query result
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("Select * from departments");


        //how to find how many rows we have for the querry
        //go to last row
        resultSet.last();

        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);
        //we need move before first row to get full list since we are at the last row right now
        resultSet.beforeFirst();




        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
