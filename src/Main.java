import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Connection.CardStatements;
import Connection.InitializeDatabase;


import java.lang.String;
import java.util.Scanner;

public class Main extends InitializeDatabase {

    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException, SQLException {
        Menu m = new Menu();
        m.menu();


    }
}