import java.sql.*;
import java.util.Scanner;
public class Database {
    Connection connection = null;
    Scanner input = new Scanner(System.in);
    ResultSet resultSet = null;

    private String id, title, genre;
    private int rating, price;

    public Connection getConnection() throws SQLException{
        return this.connection != null ? this.connection :DriverManager.getConnection(
                "jdbc:sqlite:D:/UpSkill/Java/Latihan/MovieRentApp/movie.db");
    }

    public Statement getStatement() throws SQLException{
        Statement statement;
        this.connection = getConnection();
        statement = this.connection.createStatement();
        return statement;
    }


    public void createDb(String idMovie, String titleMovie, String genreMovie, int ratingMovie, int priceMovie){
        try {
            Class.forName("org.sqlite.JDBC");
            getConnection();

            getStatement().executeUpdate("insert into listmovie values('"+idMovie+"','"+titleMovie+"','"+genreMovie+"'," +
                    ""+ratingMovie+","+priceMovie+")");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void readDbTitle(){
        int i = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            getConnection();

            resultSet = getStatement().executeQuery("select * from listmovie ORDER BY title Asc;");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void readDbGenre(){
        int i = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            getConnection();

            resultSet = getStatement().executeQuery("select * from listmovie ORDER BY genre Asc;");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String readId(String id){
        this.id = id;
        try {
            Class.forName("org.sqlite.JDBC");
            getConnection();

            resultSet = getStatement().executeQuery("select * from listmovie where id = '"+id+"'");

        } catch (Exception e){
            System.out.println(e);
        }
        return id;
    }

    public void deleteDb(String id){
        try {
            Class.forName("org.sqlite.JDBC");
            getConnection();

            getStatement().executeUpdate("delete from listmovie where id= '"+id+"'");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean findMovie(String id) throws SQLException {
        this.id = id;
        try {
            Class.forName("org.sqlite.JDBC");
            getConnection();

            resultSet = getStatement().executeQuery("select * from listmovie where id = '"+id+"'");

        } catch (Exception e){
            System.out.println(e);
        }
        return !this.clearS();
    }
    public boolean clearS() throws SQLException {
        return (!this.resultSet.isBeforeFirst() && this.resultSet.getRow() == 0) || this.resultSet == null;
    }
}
