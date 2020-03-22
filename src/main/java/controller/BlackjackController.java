package controller;

import domain.PlayerIntentionType;
import domain.card.CardRepository;
import domain.card.Deck;
import domain.result.Result;
import domain.user.*;
import view.InputView;

import java.util.stream.Collectors;

import static view.InputView.*;
import static view.OutputView.*;

public class BlackjackController {
	private final Deck deck;
	private final Dealer dealer;
	private final Players players;

	public BlackjackController() {
		deck = new Deck(CardRepository.toList());
		dealer = new Dealer();
		players = enrollPlayers();
	}

	private Players enrollPlayers() {
		Names names = Names.of(InputView.inputPlayerNames());

		return Players.of(names.getNames().stream()
				.map(name ->
						new Player(name, new BettingMoney(InputView.inputPlayerBettingMoney(name))))
				.collect(Collectors.toList()));
	}

	public void run() {
		deck.shuffle();
		dealer.addInitialCards(deck);
		for (Player player : players) {
			player.addInitialCards(deck);
		}
		printInitialStatus(dealer.openCard(), players);

		if (dealer.isNotBlackjack()) {
			players.forEach(player -> proceedExtraDraw(player, deck));
			while (dealer.canDrawMore()) {
				dealer.addCard(deck);
				printDealerDrawing();
			}
		}

		printResult(dealer, players);
	}

	private void proceedExtraDraw(Player player, Deck deck) {
		while (player.canDrawMore() && wantDraw(player)) {
			player.addCard(deck);
			printCardsStatusOf(player);
		}
	}

	private boolean wantDraw(Player player) {
		return PlayerIntentionType.isYes(PlayerIntentionType.of(inputPlayerIntention(player)));
	}

	private void printResult(Dealer dealer, Players players) {
		printResultStatus(dealer, players);
		printTotalResult(Result.from(dealer, players), players);
	}
}
