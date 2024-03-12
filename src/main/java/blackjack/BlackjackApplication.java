package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {

	public static void main(String[] args) {

		BlackjackController blackjackController = new BlackjackController(InputView.getInstance(),
			OutputView.getInstance());

		blackjackController.startGame();
	}
}
