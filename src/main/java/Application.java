import domain.CardDeck;
import domain.Dealer;
import domain.Player;
import domain.PlayerFactory;
import domain.Players;
import domain.ResultCalculator;
import domain.WhetherAddCard;
import view.InputView;
import view.OutputView;

public class Application {
	public static void main(String[] args) {
		CardDeck cardDeck = new CardDeck();
		Dealer dealer = new Dealer();
		Players players = new Players(PlayerFactory.create(InputView.inputNames()));
		cardDeck.shuffle();
		giveTwoCard(cardDeck, dealer, players);
		if (dealer.isNotBlackJack()) {
			askMoreCard(cardDeck, dealer, players);
			addCardIfNeed(cardDeck, dealer);
		}
		OutputView.printUsersResult(dealer, players);
		OutputView.printLastResult(ResultCalculator.getResults(dealer, players));
	}

	private static void giveTwoCard(CardDeck cardDeck, Dealer dealer, Players players) {
		dealer.addCard(cardDeck.drawOne());
		dealer.addCard(cardDeck.drawOne());
		players.forEach(player -> {
			dealer.giveOneCard(cardDeck, player);
			dealer.giveOneCard(cardDeck, player);
		});
		OutputView.printDistributeMessage(players);
		OutputView.printInitStatus(dealer, players);
	}

	private static void askMoreCard(CardDeck cardDeck, Dealer dealer, Players players) {
		players.forEach(player -> addCardIfWant(cardDeck, dealer, player));
	}

	private static void addCardIfWant(CardDeck cardDeck, Dealer dealer, Player player) {
		while (player.isNotBust() && WhetherAddCard.of(InputView.inputMoreCard(player)).isYes()) {
			dealer.giveOneCard(cardDeck, player);
			OutputView.printStatus(player.getName(), player.getCards());
		}
	}

	private static void addCardIfNeed(CardDeck cardDeck, Dealer dealer) {
		if (dealer.shouldAddCard()) {
			dealer.addCard(cardDeck.drawOne());
			OutputView.printDealerAddCard();
		}
	}
}
