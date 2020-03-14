import static view.InputView.*;
import static view.OutputView.*;

import domain.card.CardRepository;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerIntentionType;
import domain.user.Players;

public class Application {
	public static void main(String[] args) {
		Dealer dealer = new Dealer();
		Players players = Players.of(inputPlayerNames());
		Deck deck = new Deck(CardRepository.toList());
		deck.shuffle();

		dealer.proceedInitialPhase(deck);
		players.forEach(player -> player.proceedInitialPhase(deck));
		printInitialStatus(dealer.openOneCard(), players);

		if (dealer.isNotBlackJack()) {
			players.forEach(player -> proceedPhaseOf(player, deck));
			proceedPhaseOf(dealer, deck);
		}

		printResultStatus(dealer.openAllCards(), players);
		printResult(dealer, players);
	}

	private static void proceedPhaseOf(Dealer dealer, Deck deck) {
		while (dealer.canDrawMore()) {
			dealer.receive(deck.pop());
			printDealerDrawing();
		}
	}

	private static void proceedPhaseOf(Player player, Deck deck) {
		while (player.canDrawMore() && PlayerIntentionType.of(inputIntentionOf(player)).isWantDraw()) {
			player.receive(deck.pop());
			printCardsStatusOf(player);
		}
	}
}

