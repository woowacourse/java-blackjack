package view;

import java.util.List;

import domain.Card;
import domain.Dealer;
import domain.Players;
import domain.User;

public class OutputView {
	public static void printInitStatus(Dealer dealer, Players players) {
		printStatus(dealer.getName(), dealer.getFirstCard());
		players.forEach(player -> printStatus(player.getName(), player.getCards()));
	}

	private static void printStatus(String name, List<Card> cards) {
		System.out.printf("%s: %s\n", name, cards);
	}

	public static void printUsersResult(Dealer dealer, Players players) {
		printUserResult(dealer);
		players.forEach(OutputView::printUserResult);
	}

	private static void printUserResult(User user) {
		System.out.printf("%s: %s - 결과: %d\n", user.getName(), user.getCards(), user.getScore());
	}
}
