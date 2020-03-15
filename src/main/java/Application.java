import static view.InputView.*;
import static view.OutputView.*;

import controller.BlackjackController;
import domain.card.CardRepository;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Players;

public class Application {
	public static void main(String[] args) {
		Dealer dealer = new Dealer();
		Players players = Players.of(inputPlayerNames());
		Deck deck = Deck.of(CardRepository.toList());

		dealer.proceedInitialPhase(deck);
		players.forEach(player -> player.proceedInitialPhase(deck));
		printInitialStatus(dealer.openOneCard(), players);

		if (dealer.isNotBlackJack()) {
			BlackjackController.proceedGame(players, dealer, deck);
		}

		printResultStatus(dealer.openAllCards(), players);
		printResult(dealer, players);
	}
}
