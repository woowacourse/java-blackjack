package blackjack;

import blackjack.controller.BlackJackGameController;
import blackjack.domain.card.RealDeck;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
	public static void main(String[] args) {
		BlackJackGameController blackJackGameController = new BlackJackGameController(new InputView(), new OutputView(), new RealDeck());
		blackJackGameController.gameStart();
	}
}
