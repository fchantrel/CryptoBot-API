package fr.fchantrel.cryptobot.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author fchantrel
 *
 */
public class CotationWithStats {

    private String symbol;

    private BigDecimal valeur;

    private Date date;

    private BigDecimal valeurOuverture;

    private Double volume;

    private Double pourcentageEvolutionPrix;

    public CotationWithStats(String symbol, BigDecimal valeur, Date date) {
        this.symbol = symbol;
        this.valeur = valeur;
        this.date = date;
    }

    public BigDecimal getValeurOuverture() {
        return valeurOuverture;
    }

    public void setValeurOuverture(BigDecimal valeurOuverture) {
        this.valeurOuverture = valeurOuverture;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getPourcentageEvolutionPrix() {
        return pourcentageEvolutionPrix;
    }

    public void setPourcentageEvolutionPrix(Double pourcentageEvolutionPrix) {
        this.pourcentageEvolutionPrix = pourcentageEvolutionPrix;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getValeur() {
        return valeur;
    }
}
