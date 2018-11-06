package fr.fchantrel.cryptobot.dao.impl;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.AssetBalance;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.market.TickerPrice;
import com.codahale.metrics.annotation.Counted;
import com.codahale.metrics.annotation.Timed;
import fr.fchantrel.cryptobot.dao.binance.DAOPortefeuille;
import fr.fchantrel.cryptobot.domain.ActifCryptomonnaie;
import fr.fchantrel.cryptobot.domain.Portefeuille;
import fr.fchantrel.cryptobot.domain.ReponseOrdre;
import fr.fchantrel.cryptobot.domain.RequeteOrdre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author fchantrel
 */
@Repository
public class DAOPortefeuilleImpl implements DAOPortefeuille {

    private static final Logger LOGGER = LoggerFactory.getLogger(DAOPortefeuilleImpl.class);

    @Value("${binance.api_key}")
    private String api_key;

    @Value("${binance.api_secret}")
    private String api_secret;

    private BinanceApiRestClient client ;

    public DAOPortefeuilleImpl() {
    }

    @PostConstruct
    public void initialiserClientBinance(){
        if(client == null){
            BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(api_key, api_secret);
            client = factory.newRestClient();
        }
    }

    /**
     * Récupère le portefeuille client
     * avec les cryptos dispos et celles bloquées (ordres en attente)
     * @return
     */
    @Timed
    @Counted
    public Portefeuille getPortefeuille(){

        Portefeuille retour = new Portefeuille();

        Account myAccount = client.getAccount();
        LOGGER.info(myAccount.toString());

        LOGGER.info(myAccount.getBalances().toString());

        myAccount.getBalances();
        for (AssetBalance currentBalance : myAccount.getBalances()) {
            Double quantiteDispo = Double.parseDouble(currentBalance.getFree());
            Double quantiteBloquee = Double.parseDouble(currentBalance.getLocked());

            if(quantiteBloquee > 0 || quantiteDispo > 0){
                ActifCryptomonnaie currentActif = new ActifCryptomonnaie();
                currentActif.setCryptomonnaie(currentBalance.getAsset());
                currentActif.setQuantiteDisponible(quantiteDispo);
                currentActif.setQuantiteBloquee(quantiteBloquee);
                // on récupère sa valeur pour calculer le montant en BTC
                if(!"BTC".equalsIgnoreCase(currentActif.getCryptomonnaie())){
                    String symbol = currentActif.getCryptomonnaie().concat("BTC");
                    TickerPrice tickerPrice = client.getPrice(symbol);
                    Double prix = Double.parseDouble(tickerPrice.getPrice());
                    double montantDisponible = prix * currentActif.getQuantiteDisponible();
                    BigDecimal big = new BigDecimal(montantDisponible).setScale(12,BigDecimal.ROUND_HALF_DOWN);
                    currentActif.setMontantDisponible(big.toPlainString());
                    double montantBloque = prix * currentActif.getQuantiteBloquee();
                    BigDecimal big2 = new BigDecimal(montantBloque).setScale(12,BigDecimal.ROUND_HALF_DOWN);
                    currentActif.setMontantBloque(big2.toPlainString());
                } else {
                    // il s'agit de BTC donc pas de conversion
                    double montantBloque = currentActif.getQuantiteBloquee();
                    BigDecimal big1 = new BigDecimal(montantBloque).setScale(12,BigDecimal.ROUND_HALF_DOWN);
                    currentActif.setMontantBloque(big1.toPlainString());
                    double montantDisponible = currentActif.getQuantiteDisponible();
                    BigDecimal big = new BigDecimal(montantDisponible).setScale(12,BigDecimal.ROUND_HALF_DOWN);
                    currentActif.setMontantDisponible(big.toPlainString());
                }
                retour.addActifCryptomonnaie(currentActif);
            } // fin du if
        }

        return retour;
    }

    /**
     * Permet de simuler un passage d'ordre
     * @return
     */
    @Timed
    @Counted
    public void simulerOrdreAchat(RequeteOrdre pRequeteOrdre){

        LOGGER.info("On passe un ordre d'achat : " + pRequeteOrdre.toString());
        client.newOrderTest(NewOrder.limitBuy(pRequeteOrdre.getSymbole(), TimeInForce.GTC, pRequeteOrdre.getQuantite().toString(), pRequeteOrdre.getPrix().toString()));
        LOGGER.info("Ordre d'achat simulé ok.");
    }

    /**
     * Permet de passer un ordre d'achat
     * @return
     */
    @Timed
    @Counted
    public ReponseOrdre passerOrdreAchat(RequeteOrdre pRequeteOrdre){

        LOGGER.info("On passe un ordre d'achat : " + pRequeteOrdre.toString());
        NewOrderResponse response = client.newOrder(NewOrder.limitBuy(pRequeteOrdre.getSymbole(), TimeInForce.GTC, pRequeteOrdre.getQuantite().toString(), pRequeteOrdre.getPrix().toString()));

        ReponseOrdre retour = new ReponseOrdre();
        retour.setRequeteOrdreAssociee(pRequeteOrdre);
        retour.setIdOrdre(response.getOrderId());
        retour.setIdOrdreClient(response.getClientOrderId());
        retour.setStatut(response.getStatus().toString());
        retour.setDateSoumission(new Date());

        LOGGER.info("Retour suite à l'ordre d'achat : " + retour.toString());

        return retour;
}

    /**
     * Permet de passer un ordre de vente
     * @return
     */
    @Timed
    @Counted
    public ReponseOrdre passerOrdreVente(RequeteOrdre pRequeteOrdre){

        LOGGER.info("On passe un ordre de vente : " + pRequeteOrdre.toString());
        NewOrderResponse response = client.newOrder(NewOrder.limitSell(pRequeteOrdre.getSymbole(), TimeInForce.GTC, pRequeteOrdre.getQuantite().toString(), pRequeteOrdre.getPrix().toString()));

        ReponseOrdre retour = new ReponseOrdre();
        retour.setRequeteOrdreAssociee(pRequeteOrdre);
        retour.setIdOrdre(response.getOrderId());
        retour.setIdOrdreClient(response.getClientOrderId());
        retour.setStatut(response.getStatus().toString());
        retour.setDateSoumission(new Date());

        LOGGER.info("Retour suite à l'ordre de vente : " + retour.toString());

        return retour;
    }

    /**
     * Permet de simuler un ordre de vente
     * @return
     */
    @Timed
    @Counted
    public void simulerOrdreVente(RequeteOrdre pRequeteOrdre){

        LOGGER.info("On passe un ordre de vente : " + pRequeteOrdre.toString());
        client.newOrderTest(NewOrder.limitSell(pRequeteOrdre.getSymbole(), TimeInForce.GTC, pRequeteOrdre.getQuantite().toString(), pRequeteOrdre.getPrix().toString()));
    }
}
