import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.step:REgistration to the driver
        Class.forName("org.postgresql.Driver");
        //2.step:Create connection with database
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","B1lgeNur");
        //3.step:Create statement
        Statement st=con.createStatement();

        //1.Example: Update the number of employees to 9999 if the company name is IBM by using prepared statement
        //1.step:Create prepared statement query
           String sql1="Update companies SET number_of_employees=? where company=?";
        //2.step:Create prepared statement object
        PreparedStatement pst1=con.prepareStatement(sql1);

        //3.Step:Assign the values by using 'setInt(),setString(),...' methods.
        pst1.setInt(1,9999);
        pst1.setString(2,"IBM");

        //4.step:Execute the query
        int numberOfRecordsUpdated=pst1.executeUpdate();


        System.out.println("numberOfRecordsUpdated"+numberOfRecordsUpdated);
        String sql2="select * from companies";
        ResultSet rs1=st.executeQuery(sql2);
        while (rs1.next()){
            System.out.println(rs1.getInt("company_id")+"->"+rs1.getString(2)+"->"+rs1.getString(3));
        }

        ////2.Example: Update the number of employees to 5555 if the company name is GOOGLE by using prepared statement

        pst1.setInt(1,5555);
        pst1.setString(2,"GOOGLE");

        //4.step:Execute the query
        int numberOfRecordsUpdated2=pst1.executeUpdate();


        System.out.println("numberOfRecordsUpdated"+numberOfRecordsUpdated);
        String sql3="select * from companies";
        ResultSet rs2=st.executeQuery(sql3);
        while (rs2.next()){
            System.out.println(rs2.getInt("company_id")+"->"+rs2.getString(2)+"->"+rs2.getString(3));
        }
        con.close();
        st.close();
        pst1.close();






    }
}
