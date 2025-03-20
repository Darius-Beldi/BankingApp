import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import Connection.CardStatements;


import java.lang.String;
import java.util.Scanner;

public class Main extends CardStatements {

    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException, SQLException {
        Menu m = new Menu();
        m.menu();


    }
}