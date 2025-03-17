import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

import Cards.Card;
import Frames.MainFrame;

import javax.swing.*;
import java.lang.String;
import java.util.List;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Functions f = new Functions();
        User[] users =  f.LoadUsers("D:\\Facultate\\An 2 Sem 2\\Programare Avansata pe Obiecte\\BankingApp\\src\\SaveData\\users.txt");
        new JButtonApp().setVisible(true);
        for(int i = 0; i<users.length; i++){
            if(users[i] != null){
                users[i].ShowDetails();
            }
        }

    }
}