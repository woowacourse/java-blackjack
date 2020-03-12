package blackjack.controller;

import java.util.List;

import blackjack.domain.card.Deck;
import blackjack.domain.user.User;
import blackjack.view.OutputView;

public class BlackjackController {
	private static final int INITIAL_DRAW_NUMBER = 2;

	private final Deck deck;
	private final List<User> users;

	public BlackjackController(Deck deck, List<User> users) {
		this.deck = deck;
		this.users = users;
	}

	public void playGame() {
		users.forEach(user -> user.draw(deck, INITIAL_DRAW_NUMBER));
		OutputView.printUsersInitialDraw(INITIAL_DRAW_NUMBER, users);
	}
}
