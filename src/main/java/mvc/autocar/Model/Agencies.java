package mvc.autocar.Model;

import javafx.collections.ObservableList;

public class Agencies {

    public static ObservableList<Agencies> getDataAgencies;
    private int idAgencie;
    private String nom;
    private String adresse;
    private String status;
    private int tel;
    private int ticketDisponible;
    private  int ticketVendu;

    public Agencies(int idAgencie, String nom, String adresse, String status, int tel, int ticketDisponible, int ticketVendu) {
        this.idAgencie = idAgencie;
        this.nom = nom;
        this.adresse = adresse;
        status = status;
        this.tel = tel;
        this.ticketDisponible = ticketDisponible;
        this.ticketVendu = ticketVendu;
    }

    public int getIdAgencie() {
        return idAgencie;
    }

    public void setIdAgencie(int idAgencie) {
        this.idAgencie = idAgencie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        status = status;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getTicketDisponible() {
        return ticketDisponible;
    }

    public void setTicketDisponible(int ticketDisponible) {
        this.ticketDisponible = ticketDisponible;
    }

    public int getTicketVendu() {
        return ticketVendu;
    }

    public void setTicketVendu(int ticketVendu) {
        this.ticketVendu = ticketVendu;
    }
}
