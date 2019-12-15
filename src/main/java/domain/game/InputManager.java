package domain.game;

import java.util.Scanner;

public class InputManager {
	private static final double DEFAULT_BETTING_MONEY = 0.0;
	private static final int FIRST_INDEX = 0;
	private static final char HIT = 'y';
	private static final char STAY = 'n';

	Scanner input = new Scanner(System.in);

	public String[] inputPlayerNames() {
		input = new Scanner(System.in);
		String[] playerNames;
		try {
			System.out.println("���ӿ� ������ ����� �̸��� �Է��ϼ���.(��ǥ �������� �и�)");
			playerNames = input.next().split(",");
			playerNamesValidation(playerNames);
			return playerNames;
		} catch (Exception e) {
			System.out.println("�߸��� �Է��Դϴ�.");
			return inputPlayerNames();
		}
	}

	private void playerNamesValidation(String[] playerNames) throws Exception {
		for (String name : playerNames) {
			if (name.contains(" ")) {
				throw new Exception();
			}
		}
	}

	public double inputBettingMoney(String name) {
		input = new Scanner(System.in);
		double bettingMoney = DEFAULT_BETTING_MONEY;
		try {
			System.out.println(name + "�� ���� �ݾ���?");
			bettingMoney = input.nextDouble();
			bettingMoneyValidation(bettingMoney);
			return bettingMoney;
		} catch (Exception e) {
			System.out.println("�߸��� �Է��Դϴ�.");
			return inputBettingMoney(name);
		}
	}

	private void bettingMoneyValidation(double bettingMoney) throws Exception {
		if (bettingMoney <= DEFAULT_BETTING_MONEY) {
			throw new Exception();
		}
	}

	public char chooseHitOrStay() {
		input = new Scanner(System.in);
		char choice = ' ';
		try {
			System.out.println("�� ���� ī�带 �� �ްڽ��ϱ�? (���� y, �ƴϿ��� n)");
			choice = input.next().charAt(FIRST_INDEX);
			choiceValidation(choice);
			return choice;
		} catch (Exception e) {
			System.out.println("�߸��� �Է��Դϴ�.");
			return chooseHitOrStay();
		}
	}

	private void choiceValidation(char choice) throws Exception {
		if (choice != HIT && choice != Character.toUpperCase(HIT) && choice != STAY && choice != Character.toUpperCase(STAY)) {
			throw new Exception();
		}
	}

}
