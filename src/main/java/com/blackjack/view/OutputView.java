package com.blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.blackjack.domain.PlayerRecords;
import com.blackjack.domain.ResultType;
import com.blackjack.domain.Score;
import com.blackjack.domain.card.Card;
import com.blackjack.domain.user.User;

public class OutputView {
	private static final String DELIMITER = ", ";

	private OutputView() {
	}

	public static void printErrorMessage(String message) {
		System.out.println(message);
	}

	public static void printCardsAtFirst(User dealer, List<User> players, int firstDrawCount) {
		printDrawTitle(players, firstDrawCount);
		System.out.println(dealer.getName() + ": " + makeDealerFirstCardInfo(dealer));
		printPlayersCardInfo(players);
		System.out.println();
	}

	public static void printUserCardInfo(User player) {
		System.out.println(makeUserCardInfo(player));
	}

	public static void printDealerDrawMessage(int drawCondition) {
		System.out.printf("딜러는 %d이하라 한 장의 카드를 더 받았습니다.\n", drawCondition);
	}

	public static void printUserScore(Score score) {
		System.out.printf(" - 결과: %s\n", score);
	}

	public static void printResultMessage() {
		System.out.println("\n## 최종 승패");
	}

	public static void printUserRecords(PlayerRecords playerRecords) {
		playerRecords.getRecords().forEach((key, value) -> System.out.printf("%s: %s\n", key.getName(), value));
	}

	public static void printDealerRecord(Map<ResultType, Long> dealerResult) {
		String result = dealerResult.entrySet().stream()
				.map(e -> e.getValue() + e.getKey().toString())
				.collect(Collectors.joining(" "));
		System.out.printf("딜러: %s\n", result);
	}

	private static void printPlayersCardInfo(List<User> players) {
		for (User player : players) {
			System.out.println(makeUserCardInfo(player));
		}
	}
	private static String makeDealerFirstCardInfo(User dealer) {
		return dealer.getHand()
				.getCards()
				.get(0)
				.toString();
	}

	private static String makeUserCardInfo(User user) {
		return user.getName() + "카드: " + makeCardInfo(user);
	}

	private static String makeCardInfo(User user) {
		return user.getHand()
				.getCards()
				.stream()
				.map(Card::toString)
				.collect(Collectors.joining(DELIMITER));
	}

	private static void printDrawTitle(List<User> players, int firstDrawCount) {
		String playerNames = players.stream()
				.map(User::getName)
				.collect(Collectors.joining(DELIMITER));
		System.out.printf("딜러와 %s에게 %d장의 카드를 나누었습니다.\n", playerNames, firstDrawCount);
	}
}