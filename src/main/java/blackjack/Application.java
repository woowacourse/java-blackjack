package blackjack;

import blackjack.controller.BlackJackGameController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
	public static void main(String[] args) {
		BlackJackGameController blackJackGameController = new BlackJackGameController(new InputView(), new OutputView());
		blackJackGameController.gameStart();
	}
}
