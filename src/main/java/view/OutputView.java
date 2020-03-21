package view;

import java.util.Map;

import domain.GameParticipant;
import domain.PlayerResult;
import domain.Players;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;

public class OutputView {

	private static final String DELIMITER = ", ";

	private static void printInitialDrawInstruction(GameParticipant participant) {
		Players players = participant.getPlayers();
		StringBuilder drawInstruction = new StringBuilder();
		String userNames = String.join(DELIMITER, players.getUserNames());
		drawInstruction.append("\n딜러와 ").append(userNames).append("에게 2장의 카드를 나누었습니다.");
		System.out.println(drawInstruction);
	}

	public static void printInitialDraw(GameParticipant participant) {
		printInitialDrawInstruction(participant);
		printDealerInitialDraw(participant.getDealer());
		printCardStatusForAllPlayers(participant.getPlayers());
	}

	private static void printDealerInitialDraw(Dealer dealer) {
		System.out.println(dealer.toStringFirstDraw());
	}

	public static void printCardStatus(Player player) {
		System.out.println(player.toString());
	}

	private static void printCardStatusForAllPlayers(Players players) {
		for (Player player : players.getPlayers()) {
			printCardStatus(player);
		}
		System.out.println();
	}

	public static void printDealerAdditionalCard(int hitNumber) {
		for (int i = 0; i < hitNumber; i++) {
			System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
		}
		System.out.println();
	}

	private static void printFinalScore(Participant participant) {
		System.out.println(participant.toString() + " - 결과: " + participant.calculateScore());
	}

	public static void printFinalScoreBoard(GameParticipant gameParticipant) {
		Dealer dealer = gameParticipant.getDealer();
		Players players = gameParticipant.getPlayers();
		printFinalScore(dealer);
		for (Player player : players.getPlayers()) {
			printFinalScore(player);
		}
	}

	public static void printFinalResult(PlayerResult playerResult) {
		System.out.println("\n## 최종 수익");
		Map<Player, Double> result = playerResult.getResult();
		printDealerResult(result);
		printPlayersResult(result);
	}

	private static void printDealerResult(Map<Player, Double> userResultMap) {
		StringBuilder sb = new StringBuilder("딜러: ");
		double dealerProfit = 0;
		for (double value : userResultMap.values()) {
			dealerProfit -= value;
		}
		sb.append(dealerProfit);
		System.out.println(sb);
	}

	private static void printPlayersResult(Map<Player, Double> userResultMap) {
		for (Player player : userResultMap.keySet()) {
			System.out.println(player.getName() + ": " + userResultMap.get(player));
		}
	}

	public static void printErrorMessage(String message) {
		System.out.println(message);
	}
}
