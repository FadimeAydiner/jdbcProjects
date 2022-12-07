import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.step:REgistration to the driver
        Class.forName("org.postgresql.Driver");
        //2.step:Create connection with database
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","B1lgeNur");
        //3.step:Create statement
        Statement st=con.createStatement();

        //1.Example: Select the country names whose region id's are 1
        String sql1="Select country_name from countries where region_id=1";

        //To see the records we use executeQuery()
        ResultSet resultSet1=st.executeQuery(sql1);
        while (resultSet1.next())
        {
            System.out.println(resultSet1.getString("country_name"));
        }

        //2.Example:Select the country_id and country_name whose region_id is grater than 2
        String sql2="Select country_id,country_name from countries where region_id>2";
        ResultSet resultSet2=st.executeQuery(sql2);
        while (resultSet2.next())
        {
            System.out.println(resultSet2.getString("country_id")+"->"+resultSet2.getString("country_name"));
        }

        //3.Example:Select the company whose number_of_employees is the lowest from companies table
        String sql3="Select * from companies order by number_of_employees limit 1";
        ResultSet resultSet3=st.executeQuery(sql3);
       resultSet3.next();
       System.out.println(resultSet3.getString("company")+"->"+resultSet3.getString("number_of_employees"));

    }
}
