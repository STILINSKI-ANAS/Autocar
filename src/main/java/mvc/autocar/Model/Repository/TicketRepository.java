package mvc.autocar.Model.Repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mvc.autocar.Model.Repository.connectivity.ConnectionClass;
import mvc.autocar.Model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketRepository {

    public ObservableList<Ticket> load() {
        ObservableList<Ticket> ticket_list = FXCollections.observableArrayList();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement("select * from ticket");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                ticket_list.add(new Ticket(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getInt(5),
                        resultSet.getDouble(6),
                        resultSet.getString(7),
                        resultSet.getTime(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10)));

            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ticket_list;
    }
}
