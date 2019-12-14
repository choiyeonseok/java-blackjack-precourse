package domain.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.user.Player;

public class BlackJackGame {
	private List<Player> playerList = new ArrayList<>();
	private String[] playerNames;
	Scanner input = new Scanner(System.in);
	
	public void play() {
		inputPlayerInfos();
	}
	
	private void inputPlayerInfos() {
		System.out.println("���ӿ� ������ ����� �̸��� �Է��ϼ���.(��ǥ �������� �и�)");
		playerNames = input.next().split(",");
	
		for (String name : playerNames) {
			System.out.println(name + "�� ���� �ݾ���?");
			double bettingMoney = input.nextDouble();
			playerList.add(new Player(name, bettingMoney));
		}
	}
	
}
