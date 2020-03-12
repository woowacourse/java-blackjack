package com.blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import com.blackjack.domain.card.Card;
import com.blackjack.domain.user.User;

public class OutputView {
	private static final String DELIMITER = ", ";
	private static final String LINE_FEED = "\n";

	private OutputView() {
	}

	public static void printCardsAtFirst(User dealer, List<User> players) {
		printDrawTitle(players);
		System.out.println(dealer.getName() + ": " + makeDealerFirstCardInfo(dealer));
		System.out.println(makePlayersCardInfo(players));
	}

	private static String makePlayersCardInfo(List<User> players) {
		return players.stream()
			.map(OutputView::makeUserCardInfo)
			.collect(Collectors.joining(LINE_FEED));
	}

	private static String makeDealerFirstCardInfo(User dealer) {
		return dealer.getHands()
			.get(0)
			.toString();
	}

	private static String makeUserCardInfo(User user) {
		return user.getName() + "카드: " + makeCardInfo(user);
	}

	private static String makeCardInfo(User user) {
		return user.getHands()
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
