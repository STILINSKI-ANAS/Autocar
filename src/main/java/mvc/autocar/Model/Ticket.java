package mvc.autocar.Model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Ticket {
    private int idTicket;
    private String destination;
    private String depart;
    private LocalDateTime dateDepart;
    private LocalDateTime dateArrive;
    private int numeroDePlace;
    private double prix;
    private String typeOfComfort;
    private int idAgence;
    private int idAdmin;
    private int idPaiment;
    private String imgSrc;

    public Ticket(int idTicket, String destination, String depart, LocalDateTime dateDepart,LocalDateTime dateArrive, int numeroDePlace, double prix, int idAdmin, int idPaiment,int idAgence,String imgSrc) {
        this.idTicket = idTicket;
        this.destination = destination;
        this.depart = depart;
        this.dateDepart = dateDepart;
        this.numeroDePlace = numeroDePlace;
        this.prix = prix;
        this.idAgence = idAgence;
        this.dateArrive = dateArrive;
        this.idAdmin = idAdmin;
        this.idPaiment = idPaiment;
        this.imgSrc= imgSrc;
    }
    public Ticket(int idTicket, String destination, String depart, LocalDateTime dateDepart,LocalDateTime dateArrive, int numeroDePlace, double prix, String typeOfComfort, int idAdmin, int idPaiment,int idAgence) {
        this.idTicket = idTicket;
        this.destination = destination;
        this.depart = depart;
        this.dateDepart = dateDepart;
        this.numeroDePlace = numeroDePlace;
        this.prix = prix;
        this.idAgence = idAgence;
        this.dateArrive = dateArrive;
        this.idAdmin = idAdmin;
        this.idPaiment = idPaiment;
        this.typeOfComfort = typeOfComfort;
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

    public LocalDateTime getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDateTime dateDepart) {
        this.dateDepart = dateDepart;
    }

    public LocalDateTime getdateArrive() {
        return dateArrive;
    }

    public void setdateArrive(LocalDateTime dateArrive) {
        this.dateArrive = dateArrive;
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

    public int getidAgence() {
        return idAgence;
    }

    public void setAgence(int idAgence) {
        this.idAgence = idAgence;
    }

    public LocalDateTime getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(LocalDateTime dateArrive) {
        this.dateArrive = dateArrive;
    }

    public String getTypeOfComfort() {
        return typeOfComfort;
    }

    public void setTypeOfComfort(String typeOfComfort) {
        this.typeOfComfort = typeOfComfort;
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

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
