package domain.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.user.Dealer;
import domain.user.GameParticipant;
import domain.user.Player;

/**
 * ���� ������ �����ϴ� ��ü
 * 
 * @author smr60
 *
 */
public class BlackJackGame {
	private static final int INITIAL_CARDS = 2;
	private static final int DEALER_BORDER_SCORE = 16;
	private static final int BLACKJACK_SCORE = 21;
	private static final char HIT = 'y';

	private InputManager inputManager;
	private CardShoe cardShoe;
	private RewardCalculator rewardCalculator;
	private GameParticipant dealer;
	private List<Player> playerList = new ArrayList<>();
	

	public BlackJackGame() {
		inputManager = new InputManager();
		cardShoe = new CardShoe();
		dealer = new Dealer();
	}

	/**
	 * ���� ���� ��ü ���� ����ϴ� ���ø� �޼���
	 */
	public void play() {
		inputPlayerInfos();
		initialDeal();
		showInitialDeal();
		rewardCalculator = new RewardCalculator(playerList);
		playersTurn();
		dealerTurn();
		showGameScore();
		showGameReward();
	}

	/**
	 * �÷��̾� ���� �Է� (�̸�, ���� �ݾ�)
	 */
	private void inputPlayerInfos() {
		for (String name : inputManager.inputPlayerNames()) {
			playerList.add(new Player(name, inputManager.inputBettingMoney(name)));
		}
	}

	/**
	 * ������ �÷��̾� ��� 2�徿 �и� �����޴� �޼���
	 */
	private void initialDeal() {
		for (int i = 0; i < INITIAL_CARDS; i++) {
			dealer.addCard(cardShoe.getOneCard());
			playerList.stream().forEach(player -> player.addCard(cardShoe.getOneCard()));
		}
	}

	private void showInitialDeal() {
		System.out
				.println("\n������ " + playerList.stream().map(player -> player.getName()).collect(Collectors.joining(","))
						+ "���� " + INITIAL_CARDS + "���� ī�带 ���������ϴ�.");
		System.out.println("���� : " + dealer.getCardInfo());
		for (Player player : playerList) {
			System.out.println(player.getName() + " : " + player.getCardInfo());
		}
	}

	/**
	 * �÷��̾� �Ѹ� ������� ��������
	 */
	private void playersTurn() {
		for (Player player : playerList) {
			eachPlayerTurn(player, player.getCardScore());
		}
	}

	/**
	 * ������ �÷��̾��� �� 
	 * 1) ���� ���̽� Ȯ�� 
	 * 2) �� ������ 21�� ����� �������� Hit �Ǵ� Stay ����
	 * 3) Bust ���̽� Ȯ��
	 * 
	 * @param player
	 */
	private void eachPlayerTurn(Player player, int playerScore) {
		if (playerScore == BLACKJACK_SCORE && playerScore != dealer.getCardScore()) {
			rewardCalculator.blackJackReward(player);
		}
		boolean hit = true;
		while (playerScore < BLACKJACK_SCORE && hit) {
			System.out.println("\n" + player.getName() + "�� ���� ������ " + playerScore + "�Դϴ�.");
			hit = isChoiceHit(inputManager.chooseHitOrStay(), player);
			playerScore = player.getCardScore();
		}
		if (playerScore > BLACKJACK_SCORE) {
			rewardCalculator.playerBustReward(player);
		}
	}

	private boolean isChoiceHit(char choice, Player player) {
		if (choice == HIT | choice == Character.toUpperCase(HIT)) {
			player.addCard(cardShoe.getOneCard());
			System.out.println(player.getName() + " : " + player.getCardInfo());
			return true;
		}
		return false;
	}

	/**
	 * ������ �� �� ������ 16������ ��� 17�̻��� �� ������ ī�� �߰�
	 */
	private void dealerTurn() {
		while (dealer.getCardScore() <= DEALER_BORDER_SCORE) {
			System.out.println("\n������ " + DEALER_BORDER_SCORE + "���϶� ������ ī�带 �� �޾ҽ��ϴ�.");
			dealer.addCard(cardShoe.getOneCard());
		}
	}

	private void showGameScore() {
		System.out.println("\n���� : " + dealer.getFinalCardInfo());
		for (Player player : playerList) {
			System.out.println(player.getName() + " : " + player.getFinalCardInfo());
		}
	}

	private void showGameReward() {
		System.out.println("\n## ���� ����");
		rewardCalculator.calculateRewards(playerList, dealer.getCardScore());
		System.out.println(rewardCalculator.showRewardInfo());
	}

}
