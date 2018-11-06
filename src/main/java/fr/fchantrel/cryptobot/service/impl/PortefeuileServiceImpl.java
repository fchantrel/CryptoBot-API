package fr.fchantrel.cryptobot.service.impl;

import fr.fchantrel.cryptobot.dao.binance.DAOPortefeuille;
import fr.fchantrel.cryptobot.domain.Portefeuille;
import fr.fchantrel.cryptobot.domain.ReponseOrdre;
import fr.fchantrel.cryptobot.domain.RequeteOrdre;
import fr.fchantrel.cryptobot.service.PortefeuilleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PortefeuileServiceImpl implements PortefeuilleService {

	private static final Logger logger = LoggerFactory.getLogger(PortefeuileServiceImpl.class.getName());

	@Autowired
	public DAOPortefeuille daoPortefeuille;

	public PortefeuileServiceImpl() {
	}

	public Portefeuille getPortefeuille() throws Exception {

		logger.info("appel de PortefeuileServiceImpl.getPortefeuille()");
		Portefeuille myPortefeuille = daoPortefeuille.getPortefeuille();

		return myPortefeuille;
	}

	public ReponseOrdre passerOrdreAchat(RequeteOrdre pRequeteOrdre) {

		logger.info("appel de PortefeuileServiceImpl.passerOrdreAchat()");
		daoPortefeuille.simulerOrdreAchat(pRequeteOrdre);

		ReponseOrdre retour = new ReponseOrdre();
		retour.setRequeteOrdreAssociee(pRequeteOrdre);
		retour.setDateSoumission(new Date());
		return retour;
	}

	public ReponseOrdre passerOrdreVente(RequeteOrdre pRequeteOrdre) {

		logger.info("appel de PortefeuileServiceImpl.passerOrdreVente()");
		daoPortefeuille.simulerOrdreVente(pRequeteOrdre);

		ReponseOrdre retour = new ReponseOrdre();
		retour.setRequeteOrdreAssociee(pRequeteOrdre);
		retour.setDateSoumission(new Date());
		return retour;
	}

}
