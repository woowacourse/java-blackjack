package blackjack;

import java.util.List;

import blackjack.controller.BlackjackController;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.PlayerFactory;
import blackjack.view.InputView;

public class Application {
	public static void main(String[] args) {
		Deck deck = new Deck();
		Dealer dealer = new Dealer(Dealer.NAME);
		List<Player> players = PlayerFactory.create(InputView.inputPlayerNames());

		BlackjackController blackjackController = new BlackjackController(deck, dealer, players);
		blackjackController.playGame();
	}
}
