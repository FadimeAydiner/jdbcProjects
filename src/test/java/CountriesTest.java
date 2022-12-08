import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountriesTest {
    /*
        Given
          User connects to the database

        When
          User sends the query to get the region ids from "countries" table

        Then
          Verify that the number of region ids greater than 1 is 17.

        And
          User closes the connection
       */

    @Test
    public void countryTest(){
        // User connects to the database
        JdbcUtils.connectToDatabase("localhost","postgres","postgres","B1lgeNur");
        JdbcUtils.createStatement();

       // User sends the query to get the region ids from "countries" table
        List<Object> column=JdbcUtils.getColumn("region_id","countries");
        System.out.println("region_ids="+column.toString());
        //Verify that the number of region ids greater than 1 is 17.
       int idsGraterThan1= column.stream().filter(t->(int)t>1).collect(Collectors.toList()).size();
        assertEquals(17,idsGraterThan1);
        //User closes the connection
        JdbcUtils.closeConnectionAndStatement();;
    }
}
