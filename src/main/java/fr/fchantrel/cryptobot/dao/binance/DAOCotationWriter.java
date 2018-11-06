package fr.fchantrel.cryptobot.dao.binance;

import fr.fchantrel.cryptobot.domain.Cotation;
import fr.fchantrel.cryptobot.domain.CotationWithStats;

import java.util.List;

/**
 * @author fchantrel
 */
public interface DAOCotationWriter {

    public void enregistrerCotations(List<Cotation> pLstCotation) ;

    public void enregistrerCotationsDetaillees(List<CotationWithStats> pLstCotation) ;

}
