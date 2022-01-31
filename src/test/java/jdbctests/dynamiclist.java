package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamiclist {
    String dbUrl = "jdbc:oracle:thin:@52.207.61.129:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void dynamicList() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //Create Statement Object and argument //ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY
        //allow us to navigate up and down in query result
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("Select first_name,last_name,salary,job_id from employees\n" +
                "where rownum <6");

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String, Object>> queryData = new ArrayList<>();

        //number of columns
        int colCount = rsMetadata.getColumnCount();

        //loop through each row
        while(resultSet.next()){
            Map<String,Object> row = new HashMap<>();

           for(int i = 1; i <= colCount; i++){
               row.put(rsMetadata.getColumnName(i),resultSet.getObject(i));
           }

            //add your map to your list
            queryData.add(row);

        }

        //print the result
        for (Map<String, Object> row :queryData){
            System.out.println(row.toString());
        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
