package blackjack;

import blackjack.game.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {
	public static void main(String[] args) {
		BlackjackGame blackjackGame = new BlackjackGame(InputView.getInstance(),
			OutputView.getInstance());

		blackjackGame.start();
	}
}
