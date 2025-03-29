import Database.Database;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database.StartConnection();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press any key to exit the program!");
        scanner.nextLine();
    }
}
