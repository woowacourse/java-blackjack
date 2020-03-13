package controller;

import domain.PlayerIntentionType;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import service.BlackjackService;

import static view.InputView.*;
import static view.OutputView.*;

public class BlackjackController {
	public static void run(Deck deck, Dealer dealer, Players players) {
		BlackjackService.giveInitialCards(deck, dealer, players);
		printInitialStatus(dealer.openCard(), players);

		if (dealer.isBlackjack()) {
			printResult(dealer, players);
			return;
		}

		players.forEach(player -> proceedExtraDraw(player, deck));

		while (dealer.canDrawMore()) {
			BlackjackService.addCard(dealer, deck);
			printDealerDrawing();
		}

		printResult(dealer, players);
	}

	private static void printResult(Dealer dealer, Players players) {
		printResultStatus(dealer.openAllCards(), players);
		printTotalResult(BlackjackService.createResult(dealer, players), players);
	}

	private static void proceedExtraDraw(Player player, Deck deck) {
		while (player.canDrawMore() && wantDraw(player)) {
			BlackjackService.addCard(player, deck);
			printCardsStatusOf(player);
		}
	}

	private static boolean wantDraw(Player player) {
		return PlayerIntentionType.YES.equals(PlayerIntentionType.of(inputPlayerIntention(player)));
	}
}
