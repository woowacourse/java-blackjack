package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

public class BlackjackTable {
	public static final int INITIAL_DRAW_NUMBER = 2;

	private final Deck deck;

	public BlackjackTable(Deck deck) {
		this.deck = deck;
	}

	public List<User> collectToUsersFrom(Dealer dealer, List<Player> players) {
		List<User> users = new ArrayList<>();
		users.add(dealer);
		users.addAll(players);
		return users;
	}

	public void drawInitialCards(List<User> users) {
		for (User user : users) {
			user.draw(deck, INITIAL_DRAW_NUMBER);
		}
	}

	public void drawCardFrom(User user) {
		user.draw(deck);
	}
}
