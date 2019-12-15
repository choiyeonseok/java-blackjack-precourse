package domain.game;

import java.util.Scanner;

/**
 * �÷��̾��� �Է°� ��ȿ�� �˻縦 ����ϴ� ��ü
 */
public class InputManager {
	private static final double DEFAULT_BETTING_MONEY = 0.0;
	private static final int FIRST_INDEX = 0;
	private static final char HIT = 'y';
	private static final char STAY = 'n';
	
	Scanner input = new Scanner(System.in);

	/**
	 * �÷��̾��� �̸� �Է� �޼���
	 * @return
	 */
	public String[] inputPlayerNames() {
		input = new Scanner(System.in);
		String[] playerNames;
		try {
			System.out.println("���ӿ� ������ ����� �̸��� �Է��ϼ���.(��ǥ �������� �и�)");
			playerNames = input.next().split(",");
			validatePlayerNames(playerNames);
			return playerNames;
		} catch (Exception e) {
			System.out.println("�߸��� �Է��Դϴ�.");
			return inputPlayerNames();
		}
	}

	private void validatePlayerNames(String[] playerNames) throws Exception {
		for (String name : playerNames) {
			if (name.contains(" ")) {
				throw new Exception();
			}
		}
	}

	/**
	 * �÷��̾��� ���� �ݾ� �Է� �޼���
	 * @param name
	 * @return
	 */
	public double inputBettingMoney(String name) {
		input = new Scanner(System.in);
		double bettingMoney = DEFAULT_BETTING_MONEY;
		try {
			System.out.println(name + "�� ���� �ݾ���?");
			bettingMoney = input.nextDouble();
			validateBettingMoney(bettingMoney);
			return bettingMoney;
		} catch (Exception e) {
			System.out.println("�߸��� �Է��Դϴ�.");
			return inputBettingMoney(name);
		}
	}

	private void validateBettingMoney(double bettingMoney) throws Exception {
		if (bettingMoney <= DEFAULT_BETTING_MONEY) {
			throw new Exception();
		}
	}

	/**
	 * �÷��̾��� Hit �Ǵ� Stay �Է� �޼���
	 * @return
	 */
	public char chooseHitOrStay() {
		input = new Scanner(System.in);
		char choice = ' ';
		try {
			System.out.println("�� ���� ī�带 �� �ްڽ��ϱ�? (���� y, �ƴϿ��� n)");
			choice = input.next().charAt(FIRST_INDEX);
			validateChoice(choice);
			return choice;
		} catch (Exception e) {
			System.out.println("�߸��� �Է��Դϴ�.");
			return chooseHitOrStay();
		}
	}

	private void validateChoice(char choice) throws Exception {
		if (choice != HIT && choice != Character.toUpperCase(HIT) && choice != STAY && choice != Character.toUpperCase(STAY)) {
			throw new Exception();
		}
	}

}
