package mvc.autocar.Model;

import java.util.Date;

public class Paiment {
    private int idPaiment;
    private Date dateDePaiment;

    public int getIdPaiment() {
        return idPaiment;
    }

    public void setIdPaiment(int idPaiment) {
        this.idPaiment = idPaiment;
    }

    public Date getDateDePaiment() {
        return dateDePaiment;
    }

    public void setDateDePaiment(Date dateDePaiment) {
        this.dateDePaiment = dateDePaiment;
    }
}
