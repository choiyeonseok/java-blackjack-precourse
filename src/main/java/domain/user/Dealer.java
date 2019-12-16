package domain.user;

import domain.card.Card;

/**
 * ���� ������ �ǹ��ϴ� ��ü
 */
public class Dealer extends GameParticipant {

	public Dealer() {
	}

	@Override
	public String getCardInfo() {
		final int FIRST_INDEX = 0;
		return cards.get(FIRST_INDEX).toString();
	}

	@Override
	public String getFinalCardInfo() {
		String cardInfo = "";
		for (Card card : cards) {
			cardInfo += card.toString() + " ";
		}
		return cardInfo + "-" + getCardScore();
	}
}
