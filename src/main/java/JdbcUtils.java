import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils {

   private static Connection connection;
   private static Statement statement;
    //1.step:REgistration to the driver
    //2.step:Create connection with database
    public static Connection connectToDatabase(String hostname,String databaseName,String userName,String password){

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
           connection= DriverManager.getConnection("jdbc:postgresql://"+hostname+":5432/"+databaseName,userName,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connection is success.");
        return  connection;

    }

    //3.step:Create statement
    public  static Statement createStatement(){
        try {
           statement= connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Statement created");
        return statement;
    }

    //4.Execute the query
    public static boolean  execute(String query){
        boolean isExecute;
        try {
            isExecute=statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Query executed");
        return isExecute;
    }
    //5.step:Close the connection
    public static void closeConnectionAndStatement(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public  static  void dropTable(String tableName){
        try {
            statement.execute("Drop table "+tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    //create table
    public static  void createTable(String tableName,String...columnName_DataType){
        StringBuilder columnDataTypeString=new StringBuilder("");
        for(String w:columnName_DataType)
        {
           columnDataTypeString.append(w).append(",");
        }
        columnDataTypeString.deleteCharAt(columnDataTypeString.lastIndexOf(","));

        try {
            statement.execute("CREATE TABLE "+tableName+"("+columnDataTypeString+")");
            System.out.println(tableName + " table is created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//insert value
    public static void insertTable(String tableName,String...columnName_Values){
        StringBuilder columnNames=new StringBuilder("");
        StringBuilder values=new StringBuilder("");
        for(String w:columnName_Values)
        { columnNames.append((w.split(" ")[0])).append(",");
            values.append((w.split(" ")[1])).append(",");}

        columnNames.deleteCharAt(columnNames.lastIndexOf(","));
        values.deleteCharAt(values.lastIndexOf(","));




        String query="INSERT INTO "+tableName+" ("+columnNames+") VALUES("+values+")";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  static List<Object> getColumn(String columnName,String tableName){
        ResultSet resultSet;
        String query="select "+columnName+" from "+tableName;
        try {
            resultSet=statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Object> data=new ArrayList<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                data.add(resultSet.getObject(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return data;
    }
}
