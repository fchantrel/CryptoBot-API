package fr.fchantrel.cryptobot.domain;

import java.util.Date;

/**
 * @author fchantrel
 * Objet métier décrivant la réponse d'un ordre d'achat ou de vente.
 *
 */
public class ReponseOrdre {

    // ordre envoyé correspondant
    private RequeteOrdre requeteOrdreAssociee;

    // date à laquelle l'ordre a été soumis
    private Date dateSoumission;

    // identifiant ordre client
    private String idOrdreClient;

    // identifiant de l'ordre
    private Long idOrdre;

    // statut de réponse de l'API
    private String statut;

    public RequeteOrdre getRequeteOrdreAssociee() {
        return requeteOrdreAssociee;
    }

    public void setRequeteOrdreAssociee(RequeteOrdre requeteOrdreAssociee) {
        this.requeteOrdreAssociee = requeteOrdreAssociee;
    }

    public Date getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(Date dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public String getIdOrdreClient() {
        return idOrdreClient;
    }

    public void setIdOrdreClient(String idOrdreClient) {
        this.idOrdreClient = idOrdreClient;
    }

    public Long getIdOrdre() {
        return idOrdre;
    }

    public void setIdOrdre(Long idOrdre) {
        this.idOrdre = idOrdre;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "ReponseOrdre{" +
                "requeteOrdreAssociee=" + requeteOrdreAssociee +
                ", dateSoumission=" + dateSoumission +
                ", idOrdreClient='" + idOrdreClient + '\'' +
                ", idOrdre=" + idOrdre +
                ", statut='" + statut + '\'' +
                '}';
    }
}
