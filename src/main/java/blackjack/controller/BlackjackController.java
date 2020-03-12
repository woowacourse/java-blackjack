package blackjack.controller;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import blackjack.view.OutputView;

public class BlackjackController {
	private static final int INITIAL_DRAW_NUMBER = 2;

	private final Deck deck;
	private final Dealer dealer;
	private final List<Player> players;

	public BlackjackController(Deck deck, Dealer dealer, List<Player> players) {
		this.deck = deck;
		this.dealer = dealer;
		this.players = players;
	}

	public void playGame() {
		List<User> users = generateUsers();

		users.forEach(user -> user.draw(deck, INITIAL_DRAW_NUMBER));
		OutputView.printUsersInitialDraw(INITIAL_DRAW_NUMBER, users);
	}

	private List<User> generateUsers() {
		List<User> users = new ArrayList<>();
		users.add(dealer);
		users.addAll(players);
		return users;
	}
}
