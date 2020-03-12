package controller;

import domain.PlayerIntentionType;
import domain.card.Deck;
import domain.result.Result;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import service.BlackjackService;
import view.InputView;

import static view.InputView.*;
import static view.OutputView.*;

public class BlackjackController {
	public static void run(Deck deck, Dealer dealer) {
		Players players = Players.of(InputView.inputPlayerNames());
		BlackjackService.giveInitialCards(deck, dealer, players);
		printInitialStatus(dealer.openOneCard(), players);

		if (dealer.isBlackJack()) {
			Result result = BlackjackService.createResult(dealer, players);
			printResult(result, dealer.openAllCards(), players);
			return;
		}

		players.forEach(player -> proceedPhaseOf(player, deck));

		while (dealer.canDrawMore()) {
			BlackjackService.addCard(dealer, deck);
			printDealerDrawing();
		}

		Result result = BlackjackService.createResult(dealer, players);
		printResult(result, dealer.openAllCards(), players);
	}

	private static void proceedPhaseOf(Player player, Deck deck) {
		while (player.canDrawMore() && wantDraw(player)) {
			BlackjackService.addCard(player, deck);
			printCardsStatusOf(player);
		}
	}

	private static boolean wantDraw(Player player) {
		return PlayerIntentionType.YES.equals(PlayerIntentionType.of(inputPlayerIntention(player)));
	}
}
