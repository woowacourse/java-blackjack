package blackjack;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {
	public static void main(String[] args) {
		BlackjackController blackjackController = new BlackjackController(InputView.getInstance(),
			OutputView.getInstance());

		Players players = blackjackController.createPlayers();
		Dealer dealer = blackjackController.createDealer();

		blackjackController.startGame(dealer, players);
		blackjackController.printResult(dealer, players);
	}
}
