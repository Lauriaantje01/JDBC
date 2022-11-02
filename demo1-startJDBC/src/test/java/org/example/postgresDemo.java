package org.example;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

import static org.assertj.core.api.Assertions.assertThat;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class postgresDemo {
@Test @Order(1)
void showThatTheConnectionToPostgresIsNotNull() throws SQLException {
    String url = "jdbc:postgresql://localhost:5432/cursistdb";
    String username = "cursist";
    String password = "PaSSw0rd";
    Connection connection = DriverManager.getConnection(url, username, password);

    assertThat(connection).isNotNull();
}

@Test
    @Order(3)
    void createTable() throws SQLException {
    Connection connection = createConnection();
    Statement statement = connection.createStatement();
    String sql = "CREATE TABLE Cursist " +
            "(ID SERIAL PRIMARY KEY     NOT NULL," +
            " NAME       TEXT    NOT NULL, " +
            " AGE        INT     NOT NULL, " +
            " COUNTRY   CHAR(50) CONSTRAINT country_name_must_be_different UNIQUE"  + ")";
    int executeUpdate = statement.executeUpdate(sql);
    assertThat(executeUpdate).isEqualTo(0);

//    Note the create Table syntax (sql)
//    The SERIAL type => auto-incrementing integer, is functionality often seen in databases
//    The executedUpdate(sql) statement should return a 0 (number zero) when a statement is executed that
//    that does not explicitly return something.
//
}

@Test
@Order(4)
void insertRows() throws SQLException {
    Connection connection = createConnection();

    connection.setAutoCommit(false);
    System.out.println("Opened database successfully");

    try {

        Statement statement = connection.createStatement();
        int numberOfRowsInserted = 0;
        String sql = "INSERT INTO Cursist (NAME, AGE, COUNTRY) " +
                "VALUES('Johan', 43, 'Nederland');";
        numberOfRowsInserted += statement.executeUpdate(sql);

        sql = "INSERT INTO Cursist (NAME, AGE, COUNTRY) " +
                "VALUES('Allen', 25, 'Texas');";
        numberOfRowsInserted += statement.executeUpdate(sql);

        assertThat(numberOfRowsInserted).isEqualTo(2);

        sql = "INSERT INTO Cursist (NAME, AGE, COUNTRY) " +
                "VALUES('Johan', 43, 'Nederland') RETURNING*;";

        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        assertThat(rs.getInt("id")).isEqualTo(3L);
        connection.commit();

    } catch (Exception e) {
        connection.rollback();
    }

    //Note the RETURNING * clause, it returns the latest issued id value

//    The last INSERT statemnt has a RETURNING clause. You can use it to get th eID immediately when the call returns.
//    We also set autoCommit on the connection to false
//    That means that the INSERT statements are not automatically permanent, the must be confirmed by a COMMIT
//    Or in the case of a failure with a ROLLBACK
//    Actually, we encounter here the concept of a transaction.
}

@Test
@Order(5)
void countNumberOfRowsInTableCursist() throws SQLException {
    Connection connection = createConnection();
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) NumberOfRows FROM Cursist");
    resultSet.next();
    long numberOfRows = resultSet.getLong("NumberOfRows");
    assertThat(numberOfRows).isEqualTo(3);
}
@Test
@Order(2)
void dropTableCursist() throws SQLException {
    Connection connection = createConnection();
    Statement statement = connection.createStatement();
    boolean execute = statement.execute("DROP TABLE Cursist;");
    assertThat(execute).isEqualTo(false);

    /*
    Returns:
    true if the first result is a ResultSet object;
    false if it is an update count or there are no results
     */
}

    private Connection createConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/cursistdb";
        String username = "cursist";
        String password = "PaSSw0rd";
        Connection connection = DriverManager.getConnection(url, username, password);
    return connection;
    }

}
