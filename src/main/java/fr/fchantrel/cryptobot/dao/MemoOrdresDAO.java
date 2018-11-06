package fr.fchantrel.cryptobot.dao;


import fr.fchantrel.cryptobot.domain.MemoOrdre;

import java.util.Iterator;
import java.util.List;

/**
 * @author fchantrel
 * DAO d'accès aux ordres entegistrés.
 *
 */
public interface MemoOrdresDAO {

    public void addMemoOrdre(MemoOrdre pMemoOrdre);

    public List<MemoOrdre> iterateAndRemove();

    public Iterator<MemoOrdre> iterator();
}
