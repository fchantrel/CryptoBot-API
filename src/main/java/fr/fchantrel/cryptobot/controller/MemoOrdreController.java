package fr.fchantrel.cryptobot.controller;

import javax.inject.Inject;

import fr.fchantrel.cryptobot.domain.MemoOrdre;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.fchantrel.cryptobot.domain.ListMemoOrdre;
import fr.fchantrel.cryptobot.service.MemoService;

import java.util.Iterator;

/**
 * Historique des derniers ordres passés
 * @author fchantrel
 *
 */
@RestController("MemoSearchController")
@RequestMapping(value = "/memo")
public class MemoOrdreController extends ExceptionController {

    @Inject
    private MemoService memoService;
    
    @RequestMapping(value = "ordres", method = RequestMethod.GET, headers = "Accept=application/json")
    public Iterator<MemoOrdre> getLastOrdres() {
        Iterator<MemoOrdre> lstMemoClic = memoService.getLastMemoOrdre();
        
        return lstMemoClic;
    }
}
