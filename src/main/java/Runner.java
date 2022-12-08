import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {
        //1.step:REgistration to the driver
        //2.step:Create connection with database
        JdbcUtils.connectToDatabase("localhost","postgres","postgres","B1lgeNur");

        //3.step:Create statement
        JdbcUtils.createStatement();

        //4.Execute the query
        //JdbcUtils.execute("CREATE TABLE workers(worker_id VARCHAR(50),worker_name VARCHAR(20),worker_salary INTEGER)");

        //drop table
        //JdbcUtils.dropTable("workers");

        //create table
        //JdbcUtils.createTable("studentTechPro","name varchar(50)","id integer","address varchar(20)","tel BIGINT");

        //Insert data to table
        JdbcUtils.insertTable("studentTechPro","name 'John'");
        JdbcUtils.insertTable("studentTechPro","name 'Mark'","id 124","address 'Texas'");

        //5.step:Close the connection
        JdbcUtils.closeConnectionAndStatement();
    }
}
