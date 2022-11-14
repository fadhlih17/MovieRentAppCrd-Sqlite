/*
 * @author (Fadhlih 202043500113)
 * @version (29-oct-2022)
 */
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);

        Menu menu = new Menu();
        boolean lanjutan = true;

        while (lanjutan){
            menu.tampilMenu();
            System.out.print("Input >> "); String ipt = input.nextLine();
            switch (ipt){
                case "1" : menu.menuAddNewMovie();
                    break;
                case "2" : menu.menuViewMovie();
                    break;
                case "3" : menu.menuRentMovie();
                    break;
                case "4" :{
                    System.out.println("Thanks to use");
                    System.exit(0);
                    break;
                }
            }
        }

    }
}