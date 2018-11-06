package fr.fchantrel.cryptobot.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Cotation {

    private String symbol;

    private BigDecimal valeur;

    private Date date;

    public Cotation(String symbol, BigDecimal valeur, Date date) {
        this.symbol = symbol;
        this.valeur = valeur;
        this.date = date;
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
