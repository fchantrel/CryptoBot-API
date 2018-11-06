package fr.fchantrel.cryptobot.service.impl;

import fr.fchantrel.cryptobot.dao.binance.DAOCotationReader;
import fr.fchantrel.cryptobot.dao.binance.DAOCotationWriter;
import fr.fchantrel.cryptobot.domain.Cotation;
import fr.fchantrel.cryptobot.domain.CotationWithStats;
import fr.fchantrel.cryptobot.service.BinanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author fchantrel
 * Service métier d'accès aux services de Binance.
 *
 */
@EnableScheduling
@Component
public class BinanceServiceImpl implements BinanceService {

	@Value("${ecriture.cotations.active}")
	private boolean active = true;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private static final Logger logger = LoggerFactory.getLogger(BinanceServiceImpl.class.getName());

	@Autowired
	public DAOCotationReader daoCotationReader;

	@Autowired
	public DAOCotationWriter daoCotationWriter;

	public BinanceServiceImpl() {
	}

	/**
	 * Enregistre les cotations de Binance
	 * Exemple de scheduling :
	 * 0 0 * * * *"         toutes les heures tous les jours
	 * @throws Exception
	 */
	// */5 * * * * *        s'exécute toutes les 5 secondes
	@Scheduled(cron = "${binance.live.schedule}")
	public void enregistrerCotations() throws Exception {
		if(active) {
			logger.info("Début enregistrement cotations");
			logger.info("Current Time      = {}", dateFormat.format(new Date()));

			List<Cotation> lstCotation = daoCotationReader.getAllCotations();

			daoCotationWriter.enregistrerCotations(lstCotation);

			logger.info("Fin enregistrement cotations");
		}
	}

	/**
	 * Enregistre les cotations de Binance
	 * Exemple de scheduling :
	 * 0 0 * * * *"         toutes les heures tous les jours
	 * @throws Exception
	 */
	@Scheduled(cron = "${binance.detail.schedule}")
	public void enregistrerCotationsDetaillees() throws Exception {
		if(active) {
			logger.info("Déclenchement du batch binance détaillé");
			logger.info("Current Time      = {}", dateFormat.format(new Date()));

			List<CotationWithStats> lstCotation = daoCotationReader.getAllCotationsWithStats();

			daoCotationWriter.enregistrerCotationsDetaillees(lstCotation);

			logger.info("Fin du batch binance détaillé");
		}
	}

}
