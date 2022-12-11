package mvc.autocar.DTO;

import java.time.LocalDate;

public class TicketSearchDTO {
    private String gareDepart;
    private String gareDarrive;
    private LocalDate dateDepart;
    private int nombreDeVoyageurs;
    private String typeDeConfort;
    private String lHeurDeDepart;

    public TicketSearchDTO(String gareDepart, String gareDarrive, LocalDate dateDepart, int nombreDeVoyageurs, String typeDeConfort, String lHeurDeDepart) {
        this.gareDepart = gareDepart;
        this.gareDarrive = gareDarrive;
        this.dateDepart = dateDepart;
        this.nombreDeVoyageurs = nombreDeVoyageurs;
        this.typeDeConfort = typeDeConfort;
        this.lHeurDeDepart = lHeurDeDepart;
    }

    public TicketSearchDTO() {
    }

    public String getlHeurDeDepart() {
        return lHeurDeDepart;
    }

    public void setlHeurDeDepart(String lHeurDeDepart) {
        this.lHeurDeDepart = lHeurDeDepart;
    }

    public String getGareDepart() {
        return gareDepart;
    }

    public void setGareDepart(String gareDepart) {
        this.gareDepart = gareDepart;
    }

    public String getGareDarrive() {
        return gareDarrive;
    }

    public void setGareDarrive(String gareDarrive) {
        this.gareDarrive = gareDarrive;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public int getNombreDeVoyageurs() {
        return nombreDeVoyageurs;
    }

    public void setNombreDeVoyageurs(int nombreDeVoyage) {
        this.nombreDeVoyageurs = nombreDeVoyage;
    }

    public String getTypeDeConfort() {
        return typeDeConfort;
    }

    public void setTypeDeConfort(String typeDeConfort) {
        this.typeDeConfort = typeDeConfort;
    }
}
