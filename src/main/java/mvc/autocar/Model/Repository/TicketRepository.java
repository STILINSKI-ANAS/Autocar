package mvc.autocar.Model.Repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mvc.autocar.DTO.TicketSearchDTO;
import mvc.autocar.Model.Repository.connectivity.ConnectionClass;
import mvc.autocar.Model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
                ticket_list.add(new Ticket(resultSet.getInt("IdTicket"),
                        resultSet.getString("Destination"),
                        resultSet.getString("Departure"),
                        resultSet.getTimestamp("DepartureDate").toLocalDateTime(),
                        resultSet.getTimestamp("ArrivalDate").toLocalDateTime(),
                        resultSet.getInt("PlaceNumber"),
                        resultSet.getDouble("Prix"),
                        resultSet.getString("TypeOfComfort"),
                        resultSet.getInt("IdAdmin"),
                        resultSet.getInt("IdPaiment"),
                        resultSet.getInt("IdAgency")
                ));

            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ticket_list;
    }

    public ObservableList<Ticket> getTickets(TicketSearchDTO ticketSearchDTO) {
        ObservableList<Ticket> ticket_list = FXCollections.observableArrayList();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        PreparedStatement pst;
        try {
            var d= ticketSearchDTO.getlHeurDeDepart();
            if(d==null)
                d="";
            String startTime, endTime;
            switch (d){
                case "Matinee":
                    startTime= "06:00:00";
                    endTime= "12:00:00";
                    break;
                case "Nuit":
                    startTime= "00:00:00";
                    endTime= "06:00:00";
                    break;
                case "Apres Midi":
                    startTime= "12:00:00";
                    endTime= "18:00:00";
                    break;
                case "Soire":
                    startTime= "18:00:00";
                    endTime= "00:00:00";
                    break;
                default:
                    startTime= "00:00:00";
                    endTime= "23:59:59";
                    break;
            }
            pst = connection.prepareStatement("select COUNT(idAgency) as nbTicket, IdTicket, Destination, Departure, DepartureDate," +
                    " ArrivalDate, PlaceNumber, Prix, TypeOfComfort, IdAdmin, IdPaiment, IdAgency from ticket where Departure=? and Destination=?" +
                    " and DATE(DepartureDate)=? and TypeOfComfort =? and TIME(DepartureDate) >= TIME(?) and " +
                    "TIME(DepartureDate) <= TIME(?) group by IdAgency HAVING COUNT(idAgency)>=?;");
            pst.setString(1, ticketSearchDTO.getGareDepart());
            pst.setString(2, ticketSearchDTO.getGareDarrive());
            pst.setString(3, ticketSearchDTO.getDateDepart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            pst.setString(4, ticketSearchDTO.getTypeDeConfort());
            pst.setString(5, startTime);
            pst.setString(6, endTime);
            pst.setInt(7, ticketSearchDTO.getNombreDeVoyageurs());
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                ticket_list.add(new Ticket(resultSet.getInt("IdTicket"),
                        resultSet.getString("Destination"),
                        resultSet.getString("Departure"),
                        resultSet.getTimestamp("DepartureDate").toLocalDateTime(),
                        resultSet.getTimestamp("ArrivalDate").toLocalDateTime(),
                        resultSet.getInt("PlaceNumber"),
                        resultSet.getDouble("Prix"),
                        resultSet.getString("TypeOfComfort"),
                        resultSet.getInt("IdAdmin"),
                        resultSet.getInt("IdPaiment"),
                        resultSet.getInt("IdAgency")
                        ));

            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ticket_list;
    }
}
