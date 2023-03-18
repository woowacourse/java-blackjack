package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.User;
import domain.Users;

public class OutputView {

	private OutputView() {
	}

	private static List<String> getPlayerNames(Users players) {
		List<String> playerNames = new ArrayList<>();
		for (User player : players.getPlayers()) {
			playerNames.add(player.getName());
		}
		return playerNames;
	}

	public static void printInitMessage(Users players) {
		List<String> playerNames = getPlayerNames(players);
		System.out.println();
		System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
	}

	public static void printDealerCardHidden(final String dealerCard) {
		System.out.println("딜러: " + dealerCard);
	}

	public static void printPlayerCards(final Map<String, List<String>> playerToCard) {
		playerToCard.forEach((name, cards) ->
			System.out.println(getEachPlayerCards(name, cards)));
	}

	public static void printDealerCards(final List<String> cards, final int score) {
		printCardWithScore("딜러 ", cards, score);
	}

	public static void printPlayerCards(final Map<String, List<String>> playerToCard,
		final Map<String, Integer> playerToScore) {
		playerToCard.forEach((name, cards) -> {
			int score = playerToScore.get(name);
			printCardWithScore(name, cards, score);
		});
	}

	public static void printEachPlayerCards(final String name, final List<String> cards) {
		System.out.println(getEachPlayerCards(name, cards));
	}

	private static void printCardWithScore(final String name, final List<String> cards, final int score) {
		System.out.println(getEachPlayerCards(name, cards) + " - 결과: " + score);
	}

	private static String getEachPlayerCards(final String name, final List<String> cards) {
		List<String> cardNames = new ArrayList<>(cards);
		return name + "카드: "
			+ String.join(", ", cardNames);
	}

	public static void printGameResult(final int dealerProfit, final Map<String, Integer> playerProfits) {
		System.out.println();
		System.out.println("## 최종 수익");
		printDealerProfit(dealerProfit);
		printPlayerProfits(playerProfits);
	}

	private static void printDealerProfit(int dealerProfit) {
		System.out.println("딜러: " + dealerProfit);
	}

	private static void printPlayerProfits(Map<String, Integer> playerProfits) {
		for (String player : playerProfits.keySet()) {
			System.out.println(player + ": " + playerProfits.get(player));
		}
	}

	public static void printDealerHitMessage() {
		System.out.println();
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}
}
