package mvc.autocar.Model.Repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mvc.autocar.DTO.TicketSearchDTO;
import mvc.autocar.Model.Repository.connectivity.ConnectionClass;
import mvc.autocar.Model.Ticket;

import java.sql.*;
import java.time.LocalDate;
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
                    " ArrivalDate, PlaceNumber, Prix, TypeOfComfort, IdAdmin, IdPaiment, IdAgency from ticket where isPurchesed=0 and Departure=? and Destination=?" +
                    " and DATE(DepartureDate)=? and TypeOfComfort =? and TIME(DepartureDate) >= TIME(?) and " +
                    "TIME(DepartureDate) <= TIME(?) group by DepartureDate, Prix, IdAgency HAVING COUNT(idAgency)>=? ;");
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

    public ObservableList<Object> setTicketPurchesed(Ticket ticket, int nombreTicket) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        PreparedStatement pst;
        ObservableList<Object> tickets_ID = FXCollections.observableArrayList();
        try {
            if(nombreTicket>1){
                pst = connection.prepareStatement("select IdTicket from ticket" +
                        " where isPurchesed=0 and Departure=? and Destination=?" +
                        " and Date(DepartureDate)=? and Time(DepartureDate)=? and TypeOfComfort =? and " +
                        " IdAgency=? and Prix=? LIMIT ?");
                pst.setString(1, ticket.getDepart());
                pst.setString(2, ticket.getDestination());
                pst.setDate(3, java.sql.Date.valueOf(ticket.getDateDepart().toLocalDate()));
                pst.setTime(4, java.sql.Time.valueOf(ticket.getDateDepart().toLocalTime()));
                pst.setString(5, ticket.getTypeOfComfort());
                pst.setInt(6, ticket.getidAgence());
                pst.setDouble(7, ticket.getPrix());
                pst.setInt(8, nombreTicket);
                ResultSet resultSet = pst.executeQuery();
                while (resultSet.next()) {
                    tickets_ID.add(resultSet.getInt("IdTicket"));
                }

                PreparedStatement pstPayment;
                pstPayment = connection.prepareStatement("insert into" +
                        " paiment(PaymentDate)" +
                        " Values(?);", Statement.RETURN_GENERATED_KEYS);
                java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                pstPayment.setTimestamp(1, date);
                pstPayment.executeUpdate();
                // Get the generated id of paiment row
                ResultSet rs = pstPayment.getGeneratedKeys();
                Integer paimentId = null;
                if (rs.next()) {
                    paimentId = rs.getInt(1);
                }
                pst = connection.prepareStatement("UPDATE ticket" +
                        " SET isPurchesed = 1,  IdPaiment = " + paimentId +
                        " WHERE IdTicket = ?;");

                for (Object id: tickets_ID
                     ) {
                    pst.setInt(1, (int)id);
                    pst.executeUpdate();
                }
            }
            else{
                tickets_ID.add(ticket.getIdTicket());
                PreparedStatement pstPayment;
                pstPayment = connection.prepareStatement("insert into" +
                        " paiment(PaymentDate)" +
                        " Values(?);", Statement.RETURN_GENERATED_KEYS);
                java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                pstPayment.setTimestamp(1, date);
                pstPayment.executeUpdate();
                // Get the generated id of paiment row
                ResultSet rs = pstPayment.getGeneratedKeys();
                Integer paimentId = null;
                if (rs.next()) {
                    paimentId = rs.getInt(1);
                }
                pst = connection.prepareStatement("UPDATE ticket" +
                        " SET isPurchesed = 1, IdPaiment = " + paimentId +
                        " WHERE IdTicket = ?;");
                pst.setInt(1, ticket.getIdTicket());
                pst.executeUpdate();
            }

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tickets_ID;
    }
}
