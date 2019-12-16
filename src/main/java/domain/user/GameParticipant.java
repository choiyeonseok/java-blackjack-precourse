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
public class GameParticipant {
	private final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}
	
	public String getInitialDealerCardInfo() {
		final int FIRST_INDEX = 0;
		return cards.get(FIRST_INDEX).toString();
	}
	
	public String getCardInfo() {
		String cardInfo = "";
		for (Card card : cards) {
			cardInfo += card.toString() + " ";
		}
		return cardInfo;
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
}
