/**
 * 
 */
package fr.fchantrel.cryptobot.domain;

import java.util.List;

/**
 * @author fchantrel
 *
 */
public class ListMemoOrdre {
	
	private List<MemoOrdre> lstMemoOrdre;

	/**
	 * 
	 */
	public ListMemoOrdre() {
	}
	
	/**
	 * 
	 */
	public ListMemoOrdre(List<MemoOrdre> pLstOrdre) {
		this.lstMemoOrdre = pLstOrdre;
	}
	
	public List<MemoOrdre> getLstMemoOrdre() {
		return lstMemoOrdre;
	}

	public void setLstMemoOrdre(List<MemoOrdre> lstMemoOrdre) {
		this.lstMemoOrdre = lstMemoOrdre;
	}

}
