import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Players;
import view.InputView;
import view.OutputView;

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
