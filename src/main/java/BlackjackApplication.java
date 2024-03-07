import java.util.Scanner;

import controller.BlackjackController;
import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Players;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {
	public static void main(String[] args) {
		InputView inputView = new InputView(new Scanner(System.in));
		OutputView outputView = new OutputView();
		BlackjackController blackjackController = new BlackjackController(inputView, outputView);

		Players players = blackjackController.createPlayers();
		Deck deck = blackjackController.createDeck();
		Dealer dealer = blackjackController.createDealer(deck);

		blackjackController.dealInitCards(dealer, players);

		blackjackController.receiveAdditionalCard(dealer, players);

		blackjackController.printTotalCardStatus(dealer, players);

		blackjackController.printGameResult(dealer, players);
	}
}
