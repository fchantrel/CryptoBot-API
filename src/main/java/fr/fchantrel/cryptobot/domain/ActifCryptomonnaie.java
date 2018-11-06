package fr.fchantrel.cryptobot.domain;

import java.math.BigDecimal;

public class ActifCryptomonnaie {

    private String cryptomonnaie;

    private String montantDisponible;

    private String montantBloque;

    private Double quantiteDisponible;

    private Double quantiteBloquee;

    public Double getQuantiteBloquee() {
        return quantiteBloquee;
    }

    public void setQuantiteBloquee(Double quantiteBloquee) {
        this.quantiteBloquee = quantiteBloquee;
    }

    public Double getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public void setQuantiteDisponible(Double quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }

    public String getCryptomonnaie() {
        return cryptomonnaie;
    }

    public void setCryptomonnaie(String cryptomonnaie) {
        this.cryptomonnaie = cryptomonnaie;
    }

    public String getMontantDisponible() {
        return montantDisponible;
    }

    public void setMontantDisponible(String montantDisponible) {
        this.montantDisponible = montantDisponible;
    }

    public String getMontantBloque() {
        return montantBloque;
    }

    public void setMontantBloque(String montantBloque) {
        this.montantBloque = montantBloque;
    }

}
