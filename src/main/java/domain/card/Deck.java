package domain.card;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.User;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
	private final Stack<Card> deck;

	public Deck(List<Card> cards) {
		deck = new Stack<>();
		cards.forEach(deck::push);
	}

	public void giveInitialCards(Dealer dealer, Players players) {
		shuffle();

		addCard(dealer);
		addCard(dealer);

		for (Player player : players) {
			addCard(player);
			addCard(player);
		}
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}

	public void addCard(User user) {
		user.addCard(deck.pop());
	}

	public Card pop() {
		return deck.pop();
	}
}
