package mvc.autocar.Model.Repository.connectivity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mvc.autocar.Model.Agencies;

import java.sql.*;

public class ConnectionClass<connection> {
public Connection connection;
    public Connection getConnection()
    {
        String dbName ="u139820734_autocar";
        String username = "u139820734_admin";
        String password = "Autocaradmin123.";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://185.166.188.206:3306/" + dbName + "?useSSL=false",username,password);

        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to database", e);
        }
        catch(ClassNotFoundException e) {
            System.out.println("null");
        }
        return connection;
    }

    public  ObservableList<Agencies> getDataAgencies() {
        Connection conn = getConnection();
        ObservableList<Agencies> list = FXCollections.observableArrayList();

        try{
            PreparedStatement ps = conn.prepareStatement("select * from Agencies");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Agencies(rs.getInt("idAgencie"), rs.getString("nom"),rs.getString("adresse"), rs.getString("status"), rs.getInt("tel"), rs.getInt("ticketDisponible"), rs.getInt("ticketDisponible") ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;

    }
}
