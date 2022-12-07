import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.step:REgistration to the driver
        Class.forName("org.postgresql.Driver");
        //2.step:Create connection with database
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","B1lgeNur");
        //3.step:Create statement
        Statement st=con.createStatement();
//1.Exampla:Find the company and number_of_employees whose number_of_employees is the second highest from the companies table
//1.way: Using OFFSET and FETCH
        String sql1="Select company,number_of_employees from companies order by number_of_employees desc offset 1 row fetch next 1 row only";
        ResultSet resultSet1=st.executeQuery(sql1);
        resultSet1.next();
        System.out.println(resultSet1.getString("company")+"->"+resultSet1.getString("number_of_employees"));
//2.way
        /*SELECT  company,  number_of_employees
FROM companies
WHERE number_of_employees = (SELECT MAX(number_of_employees)
							FROM companies
							WHERE number_of_employees < (SELECT MAX(number_of_employees) FROM companies)); */

        //--2.Example:Find the company and number_of_employees whose number_of_employees is less than the avarage number of employees
        String sql2="select company,number_of_employees from companies where number_of_employees <(select avg(number_of_employees) from companies)";
        ResultSet rs2=st.executeQuery(sql2);
        while (rs2.next()){
            System.out.println(rs2.getString("company")+"->"+rs2.getInt("number_of_employees"));
        }
    }
}
