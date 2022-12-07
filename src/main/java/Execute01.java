import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.step:REgistration to the driver
        Class.forName("org.postgresql.Driver");
        //2.step:Create connection with database
        Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","B1lgeNur");
        //3.step:Create statement
        Statement st=con.createStatement();

        //4.Execute the query
        /*execute() can be used in DDL(Data Defination Languaage)(Create table,drop table,alter table) and DQL(Data Query Language)(Select)
        1)If you use execute() in DDL you will get false everytime(because we are not getting any data)
        2)If you use execute() in DQL you will get true or false everytime
            When you use execute() int DQL,if you get Resultset Object as return you will get true
            otherwise you will get false
         */



        //--1.Example: Create a workers table with the columns worker_id,worker_name,worker_salary
        String sql1="CREATE TABLE workers(worker_id VARCHAR(50),worker_name VARCHAR(20),worker_salary INTEGER)";
        st.execute(sql1);

       // --2.Example Alter table by adding worker_address
        String sql2="ALTER TABLE workers ADD worker_address VARCHAR(80)";
        st.execute(sql2);

        //--3.example drop workers table
        String sql3="DROP TABLE workers";
        st.execute(sql3);
        //5.step:Close the connection
        con.close();
        st.close();

    }
}
