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

	public static void printGameResult(final Map<String, String> dealerResult,
		final Map<String, String> playerResults) {
		System.out.println();
		System.out.println("## 최종 승패");
		printDealerResult(dealerResult);
		printPlayerResults(playerResults);
	}

	private static void printDealerResult(final Map<String, String> dealerResult) {
		String name = dealerResult.keySet().toArray()[0].toString();
		String result = dealerResult.values().toArray()[0].toString();
		System.out.println(name + ": " + result);
	}

	private static void printPlayerResults(final Map<String, String> playerResults) {
		for (String player : playerResults.keySet()) {
			System.out.println(player + ": " + playerResults.get(player));
		}
	}

	public static void printDealerHitMessage() {
		System.out.println();
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
	}
}
