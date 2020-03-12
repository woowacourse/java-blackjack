package com.blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import com.blackjack.domain.card.Card;
import com.blackjack.domain.user.User;

public class OutputView {
	private static final String DELIMITER = ", ";

	private OutputView() {
	}

	public static void printCardsAtFirst(User dealer, List<User> players) {
		printDrawTitle(players);
		System.out.println(dealer.getName() + ": " + makeDealerFirstCardInfo(dealer));
		printPlayersCardInfo(players);
	}

	public static void printUserCardInfo(User player) {
		System.out.println(makeUserCardInfo(player));
	}

	public static void printDealerDrawMessage() {
		System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
	}

	public static void printCards(User dealer, List<User> players) {
		System.out.printf("%s - 결과: %s\n", makeUserCardInfo(dealer), makeDealerScore(dealer));
		for (User player : players) {
			System.out.printf("%s - 결과: %s\n", makeUserCardInfo(player), makePlayerScore(player));
		}
	}

	private static String makePlayerScore(User player) {
		return player.getHands().calculateScore().toString();
	}

	private static String makeDealerScore(User dealer) {
		return dealer.getHands().calculateScore().toString();
	}

	private static void printPlayersCardInfo(List<User> players) {
		for (User player : players) {
			System.out.println(makeUserCardInfo(player));
		}
	}

	private static String makeDealerFirstCardInfo(User dealer) {
		return dealer.getHands()
			.getCards()
			.get(0)
			.toString();
	}

	private static String makeUserCardInfo(User user) {
		return user.getName() + "카드: " + makeCardInfo(user);
	}

	private static String makeCardInfo(User user) {
		return user.getHands()
			.getCards()
			.stream()
			.map(Card::toString)
			.collect(Collectors.joining(DELIMITER));
	}

	private static void printDrawTitle(List<User> players) {
		String playerNames = players.stream()
			.map(User::getName)
			.collect(Collectors.joining(DELIMITER));
		System.out.println("딜러와 " + playerNames + "에게 2장의 카드를 나누었습니다.");
	}

}
