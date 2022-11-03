package mvc.autocar.Model;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class Ticket {
    private int idTicket;
    private String destination;
    private String depart;
    private Date dateDepart;
    private int numeroDePlace;
    private double prix;
    private String agence;
    private Time duree;
    private int idAdmin;
    private int idPaiment;

    public Ticket(int idTicket, String destination, String depart, Date dateDepart, int numeroDePlace, double prix, String agence, Time duree, int idAdmin, int idPaiment) {
        this.idTicket = idTicket;
        this.destination = destination;
        this.depart = depart;
        this.dateDepart = dateDepart;
        this.numeroDePlace = numeroDePlace;
        this.prix = prix;
        this.agence = agence;
        this.duree = duree;
        this.idAdmin = idAdmin;
        this.idPaiment = idPaiment;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public int getNumeroDePlace() {
        return numeroDePlace;
    }

    public void setNumeroDePlace(int numeroDePlace) {
        this.numeroDePlace = numeroDePlace;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public Time getDuree() {
        return duree;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public int getIdPaiment() {
        return idPaiment;
    }

    public void setIdPaiment(int idPaiment) {
        this.idPaiment = idPaiment;
    }
}
