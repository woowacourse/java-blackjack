package controller;

import domain.PlayerIntentionType;
import domain.card.CardRepository;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import service.BlackjackService;
import view.InputView;

import static view.InputView.*;
import static view.OutputView.*;

public class BlackjackController {
	private final Deck deck;
	private final Dealer dealer;
	private final Players players;

	public BlackjackController() {
		deck = new Deck(CardRepository.toList());
		dealer = new Dealer();
		players = Players.of(InputView.inputPlayerNames());
	}

	public void run() {
		BlackjackService.giveInitialCards(deck, dealer, players);
		printInitialStatus(dealer.openCard(), players);

		if (dealer.isNotBlackjack()) {
			players.forEach(player -> proceedExtraDraw(player, deck));
			while (dealer.canDrawMore()) {
				BlackjackService.addCard(dealer, deck);
				printDealerDrawing();
			}
		}

		printResult(dealer, players);
	}

	private void printResult(Dealer dealer, Players players) {
		printResultStatus(dealer.openAllCards(), players);
		printTotalResult(BlackjackService.createResult(dealer, players), players);
	}

	private void proceedExtraDraw(Player player, Deck deck) {
		while (player.canDrawMore() && wantDraw(player)) {
			BlackjackService.addCard(player, deck);
			printCardsStatusOf(player);
		}
	}

	private boolean wantDraw(Player player) {
		return PlayerIntentionType.YES.equals(PlayerIntentionType.of(inputPlayerIntention(player)));
	}
}
