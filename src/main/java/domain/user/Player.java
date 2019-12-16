package domain.user;

/**
 * ���� �����ڸ� �ǹ��ϴ� ��ü
 */
public class Player extends GameParticipant {
	private final String name;
	private final double bettingMoney;
	

	public Player(String name, double bettingMoney) {
		this.name = name;
		this.bettingMoney = bettingMoney;
	}

	public String getName() {
		return name;
	}

	public double getBettingMoney() {
		return bettingMoney;
	}
}
