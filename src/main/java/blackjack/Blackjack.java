package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Drawable;
import blackjack.domain.card.ShuffledDeckFactory;
import blackjack.domain.user.*;
import blackjack.domain.user.exceptions.AbstractPlayerException;
import blackjack.domain.user.exceptions.PlayerException;
import blackjack.domain.user.exceptions.PlayersException;
import blackjack.domain.user.exceptions.YesOrNoException;
import blackjack.view.ErrorView;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Blackjack {

	public static void main(String[] args) {
		Players players = preparePlayers();
		Playable dealer = Dealer.dealer();
		Drawable deck = prepareDeck();

		start(players, dealer, deck);

		progressPlayers(players, deck);
		progressDealer(dealer, deck);

		finish(players, dealer);
	}

	private static Players preparePlayers() {
		Players players;
		do {
			players = preparePlayersIfValid();
		} while (players == null);
		return players;
	}

	private static Players preparePlayersIfValid() {
		try {
			return Players.of(InputView.inputPlayerNames());
		} catch (PlayersException | PlayerException |AbstractPlayerException e) {
			ErrorView.printMessage(e);
			return null;
		}
	}

	private static Deck prepareDeck() {
		return Deck.ofDeckFactory(new ShuffledDeckFactory());
	}

	private static void start(Players players, Playable dealer, Drawable deck) {
		players.giveTwoCardsEachPlayer(deck);

		dealer.giveCards(deck.drawTwoCards());

		OutputView.printStartInfo(dealer, players);
	}

	private static void progressPlayers(Players players, Drawable deck) {
		for (Playable player : players.getPlayers()) {
			progressPlayer(player, deck);
		}
	}

	private static void progressPlayer(Playable player, Drawable deck) {
		while (willProgress(player)) {
			player.giveCard(deck.draw());
			OutputView.printPlayerCard(player);
		}
	}

	private static boolean willProgress(Playable player) {
		if (!player.canReceiveCard()) {
			return false;
		}
		YesOrNo yesOrNo = prepareYesOrNo(player);
		return yesOrNo.isYes();
	}

	private static YesOrNo prepareYesOrNo(Playable player) {
		YesOrNo yesOrNo;
		do {
			yesOrNo = prepareYesOrNoIfValid(player);
		} while (yesOrNo == null);
		return yesOrNo;
	}

	private static YesOrNo prepareYesOrNoIfValid(Playable player) {
		try {
			return YesOrNo.of(InputView.inputYesOrNo(player));
		} catch (YesOrNoException e) {
			ErrorView.printMessage(e);
			return null;
		}
	}

	private static void progressDealer(Playable dealer, Drawable deck) {
		while (dealer.canReceiveCard()) {
			dealer.giveCard(deck.draw());
			OutputView.printDealerTurn(dealer);
		}
	}

	private static void finish(Players players, Playable dealer) {
		OutputView.printFinalInfo(dealer, players);
		Results results = Results.of(dealer, players);
		OutputView.printResult(results);
	}
}
