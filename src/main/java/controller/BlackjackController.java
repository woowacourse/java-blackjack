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
	private static final int INITIAL_CARDS_SIZE = 2;

	private final BlackjackService service;

	public BlackjackController(BlackjackService service) {
		this.service = service;
	}

	public void run() {
		Deck deck = service.getShuffledDeck();
		Dealer dealer = service.createDealer();
		Players players = Players.of(InputView.inputPlayerNames());

		giveInitialCards(dealer, players, deck);
		printInitialStatus(dealer.openOneCard(), players);

		if (dealer.isBlackJack()) {
			final Result result = service.createResult(dealer, players);
			printResult(result, dealer.openAllCards(), players);
			return;
		}

		players.forEach(player -> proceedPhaseOf(player, deck));

		while (dealer.canDrawMore()) {
			service.addCard(dealer, deck);
			printDealerDrawing();
		}

		final Result result = service.createResult(dealer, players);
		printResult(result, dealer.openAllCards(), players);
	}

	private void giveInitialCards(Dealer dealer, Players players, Deck deck) {
		for (int i = 0; i < INITIAL_CARDS_SIZE; i++) {
			service.addCard(dealer, deck);
			players.forEach(player -> service.addCard(player, deck));
		}
	}

	private void proceedPhaseOf(Player player, Deck deck) {
		while (player.canDrawMore() && wantDraw(player)) {
			service.addCard(player, deck);
			printCardsStatusOf(player);
		}
	}

	private boolean wantDraw(Player player) {
		return PlayerIntentionType.YES.equals(PlayerIntentionType.of(inputPlayerIntention(player)));
	}
}
