package controller;

import static view.InputView.*;
import static view.OutputView.*;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerIntentionType;
import domain.user.Players;

public class BlackjackController {
	public static void proceedGame(Players players, Dealer dealer, Deck deck) {
		players.forEach(player -> proceedPhaseOf(player, deck));
		proceedPhaseOf(dealer, deck);
	}

	private static void proceedPhaseOf(Player player, Deck deck) {
		while (player.canDrawMore() && PlayerIntentionType.of(inputIntentionOf(player)).isWantDraw()) {
			player.receive(deck.pop());
			printCardsStatusOf(player);
		}
	}

	private static void proceedPhaseOf(Dealer dealer, Deck deck) {
		while (dealer.canDrawMore()) {
			dealer.receive(deck.pop());
			printDealerDrawing();
		}
	}
}
