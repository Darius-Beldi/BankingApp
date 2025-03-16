import java.util.Random;
import Cards.Card;
import java.lang.String;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Functions  f = new Functions();

        if(f.StartUp())
            f.Login();
        else f.Register();




    }
}