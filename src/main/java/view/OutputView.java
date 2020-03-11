package view;

import java.util.List;

import domain.Card;
import domain.Dealer;
import domain.Players;

public class OutputView {
	public static void printInitStatus(Dealer dealer, Players players) {
		printStatus(dealer.getName(), dealer.getFirstCard());
		players.forEach(player -> printStatus(player.getName(), player.getCards()));
	}

	private static void printStatus(String name, List<Card> cards) {
		System.out.printf("%s: %s\n", name, cards);
	}
}
