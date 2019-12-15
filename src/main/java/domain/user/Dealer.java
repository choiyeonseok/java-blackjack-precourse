package domain.user;

import domain.card.Card;
import domain.card.Symbol;

import java.util.ArrayList;
import java.util.List;

/**
 * ���� ������ �ǹ��ϴ� ��ü
 */
public class Dealer {
	private final List<Card> cards = new ArrayList<>();

	public Dealer() {
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public String showInitialCardInfo() {
		final int FIRST_INDEX = 0;
		return cards.get(FIRST_INDEX).toString();
	}
	
	public String showCardInfo() {
		String cardInfo = "";
		for (Card card : cards) {
			cardInfo += card.toString() + " ";
		}
		return cardInfo;
	}

	public int getDealerScore() {
		int score = cards.stream().mapToInt(card -> card.getScore()).sum();
		return aceScoreChange(score);

	}

	/**
	 * ���̽� ī���� ������ 1 �Ǵ� 11�� �����ϰ� ���
	 * @param score
	 * @return
	 */
	private int aceScoreChange(int score) {
		boolean hasAce = false;
		for (Card card : cards) {
			if (card.getSymbol().equals(Symbol.ACE)) {
				hasAce = true;
			}
		}
		if (hasAce && score <= Symbol.ACE.getScore() + Symbol.TEN.getScore()) {
			return score + Symbol.TEN.getScore();
		}
		return score;
	}
}
