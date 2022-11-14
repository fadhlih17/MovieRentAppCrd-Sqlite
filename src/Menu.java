import java.sql.SQLException;
import java.util.Scanner;
public class Menu {
    private String titleMovie, genreMovie, idMovie;
    private int priceMovie, ratingMovie;
    Scanner input = new Scanner(System.in);
    Database db = new Database();
    Main maina = new Main();

    public void tampilMenu() {
        System.out.println("""
                ==============
                 MOVIE RENTAL
                ==============
                1. Add New Movie
                2. View Movie (Sort by title Ascending)
                3. Rent Movie
                4. EXIT""");
    }

    public void menuAddNewMovie() {
        boolean boolTitle = true;
        boolean boolGenre = true;
        boolean boolRating = true;
        boolean boolId = true;

        String title = null, genre = null, id = null;
        int rating = 0, price = 0;

        while (boolTitle) {
            this.titleMovie = title;
            System.out.print("Input movie title [Length must be 3 beetween 20 chars] : ");
            title = this.input.nextLine();

            if (title.length() < 3 || title.length() > 20) {
                boolTitle = true;
            } else {
                boolTitle = false;
            }
        }
        while (boolGenre) {
            System.out.print("Input Movie Genre [Horror | Comedy | Action] : ");
            genre = this.input.nextLine();

            if (genre.equals("Horror") || genre.equals("Comedy") || genre.equals("Action")) {
                boolGenre = false;
            } else {
                boolGenre = true;
            }


        }
        while (boolRating) {
            try {
                System.out.print("Input Movie Rating [1-10] : ");
                rating = Integer.parseInt(this.input.nextLine());
                if (rating >= 1 && rating <= 10) {
                    boolRating = false;
                } else {
                    boolRating = true;
                }
            } catch (Exception e){

            }

        }
        while (boolId) {
            System.out.print("Generate Movie Id : ");
            id = this.input.nextLine();

            if (id.matches("[A-Z]{2}\\d{3}")) {
                boolId = false;
            } else {
                boolId = true;
            }
        }
        switch (genre) {
            case "Comedy":
                price = (title.length() * 500) + 3000;
                break;
            case "Action":
                price = (title.length() * 500) + 4000;
                break;
            case "Horror":
                price = (title.length() * 500) + 5000;
                break;
        }
        db.createDb(id, title, genre, rating, price);
        System.out.println("");
        System.out.println("Insert Success");
        System.out.println("");
        try {
            this.menuViewMovie();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void menuViewMovie() throws SQLException {
        int i = 0;
        db.readDbTitle();
        boolean tesMenu = true;

        System.out.println("================================================================");
        System.out.printf("| %-2s | %-5s | %-20s | %-6s | %-6s | %-6s | \n", "NO", "ID", "Title", "Genre", "Rating", "Price");
        System.out.println("================================================================");

        try {
            if (db.resultSet.getString("id").isEmpty()) ;
        } catch (Exception e) {
            System.out.println("No Movie Found");
        }

        while (db.resultSet.next()) {
            try {
                i++;
                String id = db.resultSet.getString("id");
                String title = db.resultSet.getString("title");
                String genre = db.resultSet.getString("genre");
                String rating = db.resultSet.getString("rating");
                String price = db.resultSet.getString("price");

                System.out.printf("| %-2d | %-5s | %-20s | %-6s | %-6s | %-6s | \n", i, id, title, genre, rating, price);


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("================================================================");

    }

    public void menuViewRentMovie() throws SQLException {
        int i = 0;
        db.readDbGenre();

        System.out.println("================================================================");
        System.out.printf("| %-2s | %-5s | %-20s | %-6s | %-6s | %-6s | \n", "NO", "ID", "Title", "Genre", "Rating", "Price");
        System.out.println("================================================================");

        try {
            if (db.resultSet.getString("id").isEmpty());
        } catch (Exception e){
            System.out.println("No Movie Found");
            System.exit(0);
        }

        while (db.resultSet.next()) {
            try {
                i++;
                String id = db.resultSet.getString("id");
                String title = db.resultSet.getString("title");
                String genre = db.resultSet.getString("genre");
                String rating = db.resultSet.getString("rating");
                String price = db.resultSet.getString("price");

                System.out.printf("| %-2d | %-5s | %-20s | %-6s | %-6s | %-6s | \n", i, id, title, genre, rating, price);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("================================================================");

    }

    public void menuRentMovie() throws SQLException {
        String id = null;
        int price = 0;
        int hasil = 0;
        boolean loopPrc = true;
        boolean loopId = true;

        this.menuViewRentMovie();

        while (loopId) {
            System.out.print("Choose Movie Id : ");
            id = this.input.nextLine();
            loopId = !db.findMovie(id);

            if (loopId) {
                System.out.println("Movie Not Found");
            } else {
                loopId = false;
            }
        }

        String readId = db.readId(id);
        int prc = db.resultSet.getInt("price");
        while (loopPrc) {
            System.out.print("Input Money to Rent[min " + prc + "] : ");
            price = Integer.parseInt(this.input.nextLine());

            if (price < prc) {
                loopPrc = true;
            } else {
                loopPrc = false;
                hasil = price - prc;
            }
        }

        System.out.println("Pay rent Successfull with " + hasil + " Change");
        db.deleteDb(id);
    }
}
