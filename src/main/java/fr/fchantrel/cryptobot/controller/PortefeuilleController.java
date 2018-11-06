package fr.fchantrel.cryptobot.controller;

import com.codahale.metrics.annotation.Counted;
import com.codahale.metrics.annotation.Timed;
import fr.fchantrel.cryptobot.domain.Portefeuille;
import fr.fchantrel.cryptobot.domain.ReponseOrdre;
import fr.fchantrel.cryptobot.domain.RequeteOrdre;
import fr.fchantrel.cryptobot.service.PortefeuilleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Etat du portefeuille client (sur Binance)
 *
 * @author fchantrel
 *
 */
@RestController("PortefeuilleController")
@RequestMapping(value = "/portefeuille")
public class PortefeuilleController extends ExceptionController {

    @Inject
    private PortefeuilleService portefeuilleService;

    @Timed
    @Counted
    @RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
    public Portefeuille getPortefeuille() throws Exception {
        Portefeuille myPortefeuille = portefeuilleService.getPortefeuille();
        
        return myPortefeuille;
    }

    @Timed
    @Counted
    @RequestMapping(value = "/ordre_achat", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ReponseOrdre passerOrdreAchatLimite(@RequestBody RequeteOrdre pRequeteOrdre) throws Exception {

        ReponseOrdre retour = portefeuilleService.passerOrdreAchat(pRequeteOrdre);

        return retour;
    }

    @Timed
    @Counted
    @RequestMapping(value = "/ordre_vente", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ReponseOrdre passerOrdreVenteLimite(@RequestBody RequeteOrdre pRequeteOrdre) throws Exception {

        ReponseOrdre retour = portefeuilleService.passerOrdreVente(pRequeteOrdre);

        return retour;
    }

}
