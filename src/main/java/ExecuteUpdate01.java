import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.step:REgistration to the driver
        Class.forName("org.postgresql.Driver");
        //2.step:Create connection with database
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","B1lgeNur");
        //3.step:Create statement
        Statement st=con.createStatement();

        //1.Example: Update the number of employees to 16000 if the number of employees is less than the average number of employees
        String sql1="Update companies SET number_of_employees=16000 where number_of_employees<(select avg(number_of_employees) from companies)";
        int numberOfRecordsUpdated=st.executeUpdate(sql1);
        System.out.println("numberOfRecordsUpdated"+numberOfRecordsUpdated);
        String sql2="select * from companies";
        ResultSet rs1=st.executeQuery(sql2);
        while (rs1.next()){
            System.out.println(rs1.getInt("company_id")+"->"+rs1.getString(2)+"->"+rs1.getString(3));
        }
        con.close();
        st.close();
    }
}
