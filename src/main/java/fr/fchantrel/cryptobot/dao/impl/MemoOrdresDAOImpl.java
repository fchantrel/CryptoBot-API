/**
 *
 */
package fr.fchantrel.cryptobot.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.fchantrel.cryptobot.dao.MemoOrdresDAO;
import fr.fchantrel.cryptobot.domain.MemoOrdre;
import fr.fchantrel.cryptobot.utils.FifoQueue;
import org.springframework.stereotype.Repository;

import com.codahale.metrics.annotation.Timed;

/**
 * @author fchantrel
 *
 */
@Repository("memoClicDAO")
public class MemoOrdresDAOImpl implements MemoOrdresDAO {

    private final FifoQueue<MemoOrdre> lstMemoOrdre = new FifoQueue<MemoOrdre>(50);

    /**
     * 
     */
    public MemoOrdresDAOImpl() {
    }

    @Override
    public void addMemoOrdre(final MemoOrdre pMemoOrdre) {
        lstMemoOrdre.add(pMemoOrdre);
    }

    @Override
    @Timed(absolute = true, name = "memo_clic")
    public List<MemoOrdre> iterateAndRemove() {
        List<MemoOrdre> retour = new ArrayList<MemoOrdre>(lstMemoOrdre.size());
        while (!lstMemoOrdre.isEmpty()) {
            retour.add(lstMemoOrdre.poll());
        }

        return retour;
    }

    @Override
    @Timed(absolute = true, name = "memo_ordre")
    public Iterator<MemoOrdre> iterator() {
        return lstMemoOrdre.iterator();
    }

}
