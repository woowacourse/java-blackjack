package controller;

import domain.PlayerIntentionType;
import domain.card.CardRepository;
import domain.card.Deck;
import domain.result.Result;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
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
		deck.giveInitialCards(dealer, players);
		printInitialStatus(dealer.openCard(), players);

		if (dealer.isNotBlackjack()) {
			players.forEach(player -> proceedExtraDraw(player, deck));
			while (dealer.canDrawMore()) {
				deck.addCard(dealer);
				printDealerDrawing();
			}
		}

		printResult(dealer, players);
	}

	private void printResult(Dealer dealer, Players players) {
		printResultStatus(dealer.openAllCards(), players);
		printTotalResult(Result.from(dealer, players), players);
	}

	private void proceedExtraDraw(Player player, Deck deck) {
		while (player.canDrawMore() && wantDraw(player)) {
			deck.addCard(player);
			printCardsStatusOf(player);
		}
	}

	private boolean wantDraw(Player player) {
		return PlayerIntentionType.isYes(PlayerIntentionType.of(inputPlayerIntention(player)));
	}
}
