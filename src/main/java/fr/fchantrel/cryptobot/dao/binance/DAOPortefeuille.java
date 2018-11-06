package fr.fchantrel.cryptobot.dao.binance;

import fr.fchantrel.cryptobot.domain.Portefeuille;
import fr.fchantrel.cryptobot.domain.ReponseOrdre;
import fr.fchantrel.cryptobot.domain.RequeteOrdre;

/**
 * @author fchantrel
 */
public interface DAOPortefeuille {
    public Portefeuille getPortefeuille();

    public void simulerOrdreAchat(RequeteOrdre pRequeteOrdre);

    public ReponseOrdre passerOrdreAchat(RequeteOrdre pRequeteOrdre);

    public void simulerOrdreVente(RequeteOrdre pRequeteOrdre);

    public ReponseOrdre passerOrdreVente(RequeteOrdre pRequeteOrdre);
}
