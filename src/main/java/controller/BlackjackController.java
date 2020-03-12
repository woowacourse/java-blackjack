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
		printInitialDistribution(players);
		printInitialStatus(dealer.openCard(), players);

		if (dealer.isBlackJack()) {
			printResultStatus(dealer.openAllCards(), players);
			printResult(BlackjackService.createResult(dealer, players), players);
			return;
		}

		players.forEach(player -> {
			while (player.canDrawMore() && wantDraw(player)) {
				BlackjackService.addCard(player, deck);
				printCardsStatusOf(player);
			}
		});

		while (dealer.canDrawMore()) {
			BlackjackService.addCard(dealer, deck);
			printDealerDraw();
		}

		printResultStatus(dealer.openAllCards(), players);
		printResult(BlackjackService.createResult(dealer, players), players);
	}

	private static boolean wantDraw(Player player) {
		return PlayerIntentionType.of(inputPlayerIntention(player)).equals(PlayerIntentionType.YES);
	}
}
