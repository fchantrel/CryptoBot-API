package fr.fchantrel.cryptobot.dao.binance;

import fr.fchantrel.cryptobot.domain.Cotation;
import fr.fchantrel.cryptobot.domain.CotationWithStats;
import fr.fchantrel.cryptobot.domain.ReponseOrdre;
import fr.fchantrel.cryptobot.domain.RequeteOrdre;

import java.util.List;

/**
 * @author fchantrel
 */
public interface DAOCotationReader {

    public List<Cotation> getAllCotations();

    public List<CotationWithStats> getAllCotationsWithStats();

}
