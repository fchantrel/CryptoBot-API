package fr.fchantrel.cryptobot.domain;

/**
 * @author fchantrel
 * Objet métier décrivant les éléments utiles pour un ordre d'achat ou de vente.
 *
 */
public class RequeteOrdre {

    // la cryptomonnaie que l'on souhaite vendre ou acheter avec la devise de reference
    // exemple : HOTBTC
    private String symbole;

    // la quantite que l'on souhaite acheter/vendre
    private Double quantite;

    // le prix auquel on souhaite acheter/vendre
    private Double prix;

    // durée limite en minutes, l'ordre devra être annulé s'il n'a pas été passé dans ce délai d'attente
    private int delaiMaximum;

    public int getDelaiMaximum() {
        return delaiMaximum;
    }

    public void setDelaiMaximum(int delaiMaximum) {
        this.delaiMaximum = delaiMaximum;
    }

    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "RequeteOrdre{" +
                "symbole='" + symbole + '\'' +
                ", quantite=" + quantite +
                ", prix=" + prix +
                ", delaiMaximum=" + delaiMaximum +
                '}';
    }
}
