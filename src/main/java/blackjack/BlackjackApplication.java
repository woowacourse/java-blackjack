package blackjack;

import blackjack.controller.BlackjackGameController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {
	public static void main(String[] args) {
		BlackjackGameController blackjackGameController = new BlackjackGameController(InputView.getInstance(),
			OutputView.getInstance());

		blackjackGameController.start();
	}
}
