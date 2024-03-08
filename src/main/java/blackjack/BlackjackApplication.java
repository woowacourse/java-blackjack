package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {
	public static void main(String[] args) {
		BlackjackController blackjackController = new BlackjackController(InputView.getInstance(),
			OutputView.getInstance());

		Players players = blackjackController.createPlayers();
		Deck deck = blackjackController.createDeck();
		Dealer dealer = blackjackController.createDealer(deck);

		blackjackController.startGame(dealer, players);
		blackjackController.printResult(dealer, players);
	}
}
