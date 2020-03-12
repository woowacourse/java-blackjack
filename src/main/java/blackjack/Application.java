package blackjack;

import java.util.ArrayList;
import java.util.List;

import blackjack.controller.BlackjackController;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.PlayerFactory;
import blackjack.domain.user.User;
import blackjack.view.InputView;

public class Application {
	public static void main(String[] args) {
		Deck deck = new Deck();
		List<User> users = generateUsers();

		BlackjackController blackjackController = new BlackjackController(deck, users);
		blackjackController.playGame();
	}

	private static List<User> generateUsers() {
		List<User> users = new ArrayList<>();

		users.add(new Dealer(Dealer.NAME));
		users.addAll(PlayerFactory.create(InputView.inputPlayerNames()));
		return users;
	}
}
