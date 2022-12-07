import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.step:REgistration to the driver
        Class.forName("org.postgresql.Driver");
        //2.step:Create connection with database
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","B1lgeNur");
        //3.step:Create statement
        Statement st=con.createStatement();
        //1.Example: Create a function which uses 2 parameters and return the sum of the parameters
        //1.step:Create function
        String sql1="CREATE FUNCTION additionF(x NUMERIC,y NUMERIC) RETURNS NUMERIC LANGUAGE plpgsql AS  $$BEGIN RETURN (x+y); END $$";
        //2.step:Execute the function
        st.execute(sql1);

        //3.step:Prepare callable statement
        CallableStatement cst1=con.prepareCall("{? = call additionF(?,?) }");

        //4.step:Use registerOutParameter() to get result for the specific  values.
        cst1.registerOutParameter(1,Types.NUMERIC);
        cst1.setInt(2,6);
        cst1.setInt(3,4);

        //        //5.step:Execute the callable statement

        cst1.execute();

        //6.step:To see the results on console use 'sout'
        System.out.println(cst1.getObject(1));



        //2.Example: Create a function which calculates the volume of cone
        //1.step:Create function
        String sql2="CREATE FUNCTION VolumeOfCone(r NUMERIC,h NUMERIC) RETURNS NUMERIC LANGUAGE plpgsql AS  $$ BEGIN RETURN 3.14*r*r*h/3; END $$";
        //2.step:Execute the function
        st.execute(sql2);

        //3.step:Prepare callable statement
        CallableStatement cst2=con.prepareCall("{? = call VolumeOfCone(?,?) }");

        //4.step:Use registerOutParameter() to get result for the specific  values.
        cst2.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,6);
        cst2.setInt(3,4);

        //        //5.step:Execute the callable statement

        cst2.execute();

        //6.step:To see the results on console use 'sout'
        System.out.println(cst2.getObject(1));

    }
}
