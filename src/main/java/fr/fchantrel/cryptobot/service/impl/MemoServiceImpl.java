/**
 * 
 */
package fr.fchantrel.cryptobot.service.impl;

import java.util.Iterator;

import javax.inject.Inject;

import com.codahale.metrics.annotation.Timed;
import fr.fchantrel.cryptobot.dao.MemoOrdresDAO;
import fr.fchantrel.cryptobot.domain.MemoOrdre;
import org.springframework.stereotype.Service;

import fr.fchantrel.cryptobot.domain.ListMemoOrdre;
import fr.fchantrel.cryptobot.service.MemoService;

/**
 * @author fchantrel
 * Service métier d'accès aux données mises en mémoire.
 *
 */
@Service("memoService")
public class MemoServiceImpl implements MemoService {

    @Inject
    private MemoOrdresDAO memoOrdreDAO;
	
	public MemoServiceImpl() {
	}

	@Override
	public void addMemoOrdre(MemoOrdre pMemoOrdre) {
		memoOrdreDAO.addMemoOrdre(pMemoOrdre);
	}

	@Override
	public Iterator<MemoOrdre> getLastMemoOrdre() {
		return memoOrdreDAO.iterator();
	}
	
	/* (non-Javadoc)
	 * @see fr.solocal.service.MemoService#getLastClics()
	 */
	@Override
	public ListMemoOrdre getAndRemoveLastMemoOrdre() {
		return new ListMemoOrdre(memoOrdreDAO.iterateAndRemove());
	}

}
