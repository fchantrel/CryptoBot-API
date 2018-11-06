package fr.fchantrel.cryptobot.service;

import fr.fchantrel.cryptobot.domain.Portefeuille;
import fr.fchantrel.cryptobot.domain.ReponseOrdre;
import fr.fchantrel.cryptobot.domain.RequeteOrdre;

public interface PortefeuilleService {
    public Portefeuille getPortefeuille() throws Exception ;

    public ReponseOrdre passerOrdreAchat(RequeteOrdre pRequeteOrdre);

    public ReponseOrdre passerOrdreVente(RequeteOrdre pRequeteOrdre);
}
