import java.util.Scanner;

import controller.BlackjackController;
import domain.Dealer;
import domain.Players;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {
	public static void main(String[] args) {
		InputView inputView = new InputView(new Scanner(System.in));
		OutputView outputView = new OutputView();
		BlackjackController blackjackController = new BlackjackController(inputView, outputView);

		Players players = blackjackController.createPlayers();
		Dealer dealer = blackjackController.createDealer();

		blackjackController.dealInitCards(dealer, players);

		blackjackController.receiveAdditionalCard(dealer, players);
	}
}
