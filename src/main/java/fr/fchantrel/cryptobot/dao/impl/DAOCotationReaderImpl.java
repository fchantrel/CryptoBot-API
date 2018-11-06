package fr.fchantrel.cryptobot.dao.impl;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;
import fr.fchantrel.cryptobot.dao.binance.DAOCotationReader;
import fr.fchantrel.cryptobot.domain.Cotation;
import fr.fchantrel.cryptobot.domain.CotationWithStats;
import fr.fchantrel.cryptobot.domain.ReponseOrdre;
import fr.fchantrel.cryptobot.domain.RequeteOrdre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author fchantrel
 */
@Repository
public class DAOCotationReaderImpl implements DAOCotationReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(DAOCotationReaderImpl.class);

    private static  final String BITCOIN = "BTC";

    @Value("${binance.api_key}")
    private String api_key;

    @Value("${binance.api_secret}")
    private String api_secret;

    private BinanceApiRestClient client ;

    public DAOCotationReaderImpl() {
    }

    @PostConstruct
    public void initialiserClientBinance(){
        if(client == null){
            BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(api_key, api_secret);
            client = factory.newRestClient();
        }
    }

    /**
     * Récupère toutes les valeurs des cryptomonnaies en BTC
     * @return
     */
    public List<Cotation> getAllCotations() {

        List<Cotation> retour = new ArrayList<Cotation>();

        List<TickerPrice> lstPrix = client.getAllPrices();
        for (int i = 0; i < lstPrix.size(); i++) {
            TickerPrice currentTicker = lstPrix.get(i);
            currentTicker.getSymbol();

            // on ne s'intéresse qu'aux crypto dont l'équivalence est donnée en BTC
            if (BITCOIN.equalsIgnoreCase(currentTicker.getSymbol().substring(3))) {
                Cotation currentCotation = new Cotation(currentTicker.getSymbol(),
                        new BigDecimal(currentTicker.getPrice()),
                        Calendar.getInstance().getTime());
                retour.add(currentCotation);
            } // fin du if

        }
        return retour;
    }

    /**
     * Récupère toutes les valeurs avec stats des cryptomonnaies en BTC
     * @return
     */
    public List<CotationWithStats> getAllCotationsWithStats(){

        List<CotationWithStats> retour = new ArrayList<CotationWithStats>();

        List<TickerStatistics> lstPrix = client.getAll24HrPriceStatistics();
        for (int i = 0; i < lstPrix.size() ; i++) {
            TickerStatistics currentTicker = lstPrix.get(i);
            currentTicker.getSymbol();

            // on ne s'intéresse qu'aux crypto dont l'équivalence est donnée en BTC
            if(BITCOIN.equalsIgnoreCase(currentTicker.getSymbol().substring(3))){
                CotationWithStats currentCotation = new CotationWithStats(currentTicker.getSymbol(),
                        new BigDecimal(currentTicker.getLastPrice()),
                        Calendar.getInstance().getTime());
                currentCotation.setPourcentageEvolutionPrix(Double.parseDouble(currentTicker.getPriceChangePercent()));
                currentCotation.setValeurOuverture(new BigDecimal(currentTicker.getOpenPrice()));
                currentCotation.setVolume(Double.parseDouble(currentTicker.getVolume()));
                retour.add(currentCotation);
            } // fin du if
        }

        return retour;
    }
}
