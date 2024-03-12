package blackjack;

import blackjack.controller.BlackjackController;

public class Application {

	public static void main(String[] args) {
		BlackjackController blackjackController = new BlackjackController();
		blackjackController.run();
	}
}
