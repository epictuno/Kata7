package kata7;

import com.google.gson.Gson;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.flight;

public class FlightsApp {

    static String url = "jdbc:sqlite:C:\\Users\\tanay\\Desktop\\flights.db";

    public FlightsApp() {
        
        try {
            Class.forName("org.sqlite.JDBC");
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(FlightsApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        connect();
    }
  
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Conectado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public String getAll() {
        String sql = "SELECT * FROM flights";
        return this.getDataFromSql(sql);
    }

    public String getFlightsOnDay(String day) {
        String sql = "SELECT * FROM flights WHERE DAY_OF_WEEK=" + day;
        return this.getDataFromSql(sql);
    }

    public String getHigherDistanceFlights(String distance) {
        String sql = "SELECT * FROM flights WHERE DISTANCE >=" + distance;
        return this.getDataFromSql(sql);
    }

    public String getLowerDistanceFlights(String distance) {
        String sql = "SELECT * FROM flights WHERE DISTANCE <=" + distance;
        return this.getDataFromSql(sql);
    }

    public String getCancelledFlights() {
        String sql = "SELECT * FROM flights WHERE CANCELLED =1";
        return this.getDataFromSql(sql);
    }

    public String getDivertedFlights() {
        String sql = "SELECT * FROM flights WHERE DIVERTED=1";
        return this.getDataFromSql(sql);

    }

    public String getDataFromSql(String sql) {
        List<flight> flights = null;
        try (Connection conn = connect(); java.sql.Statement stmt = 
            conn.createStatement(); ResultSet set = stmt.executeQuery(sql)) {
            flights = this.resultSetToList(set);
        } catch (SQLException e) {
            //reportar error sql
            System.out.println(e.getMessage());
        }
        return this.SerializeList(flights);
    }

    private String SerializeList(List<flight> flights) {
        return new Gson().toJson(flights);
    }

    private String getNumberOfDay(String day) {
        String sql = "SELECT * FROM flights WHERE DIVERTED=1";
        return this.getDataFromSql(sql);
    }

    private List<flight> resultSetToList(ResultSet set) throws SQLException {
        List<flight> flights = new ArrayList<>();
        while (set.next()) {
            flights.add(new flight(
                    set.getInt(1),
                    set.getInt(2),
                    set.getInt(3),
                    set.getInt(4),
                    set.getInt(5),
                    set.getInt(6),
                    set.getInt(7),
                    set.getInt(8),
                    set.getInt(9))
            );
        }
        return flights;
    }

}
