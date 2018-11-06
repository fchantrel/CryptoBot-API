package fr.fchantrel.cryptobot.domain;

import java.util.ArrayList;
import java.util.List;

public class Portefeuille {

    private List<ActifCryptomonnaie> lstActifCryptomonnaie = new ArrayList<ActifCryptomonnaie>();

    public List<ActifCryptomonnaie> getLstActifCryptomonnaie() {
        return lstActifCryptomonnaie;
    }

    public void setLstActifCryptomonnaie(List<ActifCryptomonnaie> lstActifCryptomonnaie) {
        this.lstActifCryptomonnaie = lstActifCryptomonnaie;
    }

    public void addActifCryptomonnaie(ActifCryptomonnaie pActifCryptomonnaie){
        lstActifCryptomonnaie.add(pActifCryptomonnaie);
    }

}
