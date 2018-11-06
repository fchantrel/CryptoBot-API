/**
 * 
 */
package fr.fchantrel.cryptobot.service;

import java.util.Iterator;

import fr.fchantrel.cryptobot.domain.ListMemoOrdre;
import fr.fchantrel.cryptobot.domain.MemoOrdre;

/**
 * @author fchantrel
 *
 */
public interface MemoService {

	public void addMemoOrdre(MemoOrdre pMemoOrdre);

	public Iterator<MemoOrdre> getLastMemoOrdre();

	public ListMemoOrdre getAndRemoveLastMemoOrdre();

}
