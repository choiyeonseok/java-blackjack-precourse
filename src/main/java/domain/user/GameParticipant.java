package domain.user;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Symbol;

/**
 * ������ �÷��̾��� �ۺ� �������̽� ������ �ϴ� ���� ��ü
 * @author smr60
 *
 */
public abstract class GameParticipant {
	protected final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}
	
	public int getCardScore() {
		int score = cards.stream()
						.mapToInt(card -> card.getScore())
						.sum();
		return changeAceScore(score);
	}

	/**
	 * ���̽� ī���� ������ 1 �Ǵ� 11�� �����ϰ� ���
	 * 
	 * @param score
	 * @return
	 */
	private int changeAceScore(int score) {
		boolean hasAce = false;
		for (Card card : cards) {
			hasAce = card.isSymbolAce();
		}
		if (hasAce && score <= Symbol.ACE.getScore() + Symbol.TEN.getScore()) {
			return score + Symbol.TEN.getScore();
		}
		return score;
	}
	
	abstract public String getCardInfo(); 

	abstract public String getFinalCardInfo();
	
}
