import Database.Database;

import java.util.Scanner;

    /*
     TODO:
     -CHECK IF DATA IS IN DB ALREADY
     */


public class Main {
    public static void main(String[] args) {
        Database.StartConnection();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press any key to exit the program!");
        scanner.nextLine();
    }
}
