package domain.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import domain.user.Dealer;
import domain.user.Player;

public class BlackJackGame {
	private static final int INITIAL_CARDS = 2;
	private static final int BLACKJACK_SCORE = 21;
	private static final char HIT = 'y';

	private CardShoe cardShoe;
	private Dealer dealer;
	private List<Player> playerList = new ArrayList<>();
	private String[] playerNames;
	
	Scanner input = new Scanner(System.in);
	
	public BlackJackGame() {
		cardShoe = new CardShoe();
		dealer = new Dealer();
	}
	
	public void play() {
		inputPlayerInfos();
		initialDeal();
		showInitialDeal();
		
		playersTurn();
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
	
	private void initialDeal() {
		for (int i = 0; i < INITIAL_CARDS; i++) {
			dealer.addCard(cardShoe.getOneCard());
			playerList.stream()
					.forEach(player -> player.addCard(cardShoe.getOneCard()));
		}
	}
	
	private void showInitialDeal() {
		System.out.println("������ " + String.join(",", Arrays.asList(playerNames)) + "���� " + INITIAL_CARDS + "���� ī�带 ���������ϴ�."); 
		System.out.print("���� : ");
		System.out.println(dealer.showCardInfo());
		for (Player player : playerList) {
			System.out.print(player.getName() + " : ");
			System.out.println(player.showCardInfo());
		}
		System.out.println();
	}
	
	private void playersTurn() {
		for (Player player : playerList) {
			eachPlayerTurn(player);
			// playerScore save to playerScoreMap
		}
	}
	
	private int eachPlayerTurn(Player player) {
		int playerScore = player.getPlayerScore();
		boolean hit = true;
		while (playerScore < BLACKJACK_SCORE && hit) {
			System.out.println(player.getName() + "�� ���� ������ " + playerScore + "�Դϴ�.\n�� ���� ī�带 �� �ްڽ��ϱ�? (���� y, �ƴϿ��� n)" );
			char choice = input.next().charAt(0);
			hit = isChoiceHit(choice, player);
			playerScore = player.getPlayerScore();
		}
		return playerScore;
	}
	
	private boolean isChoiceHit(char choice, Player player) {
		if (choice == HIT) {
			player.addCard(cardShoe.getOneCard());
			System.out.print(player.getName() + " : ");
			System.out.println(player.showCardInfo());
			return true;
		}
		return false;
	}
	
}
