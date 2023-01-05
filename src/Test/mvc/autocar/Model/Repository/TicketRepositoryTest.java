package mvc.autocar.Model.Repository;

import javafx.collections.ObservableList;
import mvc.autocar.DTO.TicketSearchDTO;
import mvc.autocar.Model.Repository.connectivity.ConnectionClass;
import mvc.autocar.Model.Ticket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {
    TicketRepository ticketRepository;
    static TicketSearchDTO ticketSearchDTO;
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        ticketSearchDTO = new TicketSearchDTO("Agadir", "rabat", LocalDate.of(2023, 1, 5), 2, "Standard", "Matinee");
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        PreparedStatement pst;
        for (int i = 0; i < ticketSearchDTO.getNombreDeVoyageurs(); i++) {
            pst = connection.prepareStatement("INSERT INTO ticket (Destination, Departure, DepartureDate, ArrivalDate, PlaceNumber, Prix, TypeOfComfort, IdAdmin, IdPaiment, IdAgency, isPurchesed)"
                    + "VALUES ('rabat', 'Agadir', '2023-01-05 06:00:00', '2023-01-05 12:00:00', '" + (i+1) +"', '260', 'Standard', '1', NULL, '35', '0');");
            pst.execute();
        }
        connection.close();
    }

    @Test
    void getTicketsTest() {
        //Arrange
        ticketRepository = new TicketRepository();

        //Act
        ObservableList<Ticket> ticketlist = ticketRepository.getTickets(ticketSearchDTO);

        //Assert
        assertEquals("rabat",ticketlist.get(0).getDestination());
        assertEquals("Agadir",ticketlist.get(0).getDepart());
    }

    @Test
    void setTicketPurchesedTest() {
        //Arrange
        ticketRepository = new TicketRepository();

        //Act
        ObservableList<Ticket> ticketlist = ticketRepository.getTickets(ticketSearchDTO);
        Ticket FirstTicket = ticketlist.get(0);
        ObservableList<Object> ticketIdList = ticketRepository.setTicketPurchesed(FirstTicket, ticketSearchDTO.getNombreDeVoyageurs());

        //Assert
        assertTrue(ticketIdList.size() == ticketSearchDTO.getNombreDeVoyageurs());
    }

}