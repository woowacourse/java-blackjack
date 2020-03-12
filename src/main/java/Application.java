import domain.CardDeck;
import domain.Dealer;
import domain.Player;
import domain.PlayersFactory;
import domain.Players;
import domain.ResultCalculator;
import domain.WhetherAddCard;
import view.InputView;
import view.OutputView;

public class Application {
	public static void main(String[] args) {
		run(new CardDeck(), new Dealer(), PlayersFactory.create((InputView.inputNames())));
	}

	private static void run(CardDeck cardDeck, Dealer dealer, Players players) {
		cardDeck.shuffle();
		giveTwoCard(cardDeck, dealer, players);
		if (dealer.isNotBlackJack()) {
			askMoreCard(cardDeck, dealer, players);
			addCardIfNeed(cardDeck, dealer);
		}
		OutputView.printCardsResults(dealer, players);
		OutputView.printGameResult(ResultCalculator.getResults(dealer, players));
	}

	private static void giveTwoCard(CardDeck cardDeck, Dealer dealer, Players players) {
		for (int i = 0; i < 2; i++) {
			dealer.giveCard(cardDeck, dealer);
			dealer.giveCard(cardDeck, players);
		}
		OutputView.printFirstDistribute(dealer, players);
	}

	private static void askMoreCard(CardDeck cardDeck, Dealer dealer, Players players) {
		players.forEach(player -> addCardIfWant(cardDeck, dealer, player));
	}

	private static void addCardIfWant(CardDeck cardDeck, Dealer dealer, Player player) {
		while (player.isNotBust() && WhetherAddCard.of(InputView.inputMoreCard(player)).isYes()) {
			dealer.giveCard(cardDeck, player);
			OutputView.printCards(player.getName(), player.getCards());
		}
	}

	private static void addCardIfNeed(CardDeck cardDeck, Dealer dealer) {
		if (dealer.shouldAddCard()) {
			dealer.addCard(cardDeck.pop());
			OutputView.printDealerAddCard();
		}
	}
}
