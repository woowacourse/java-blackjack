package blackjack;

import blackjack.domain.Result;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.DefaultDealer;
import blackjack.domain.user.Players;
import blackjack.domain.user.Player;
import blackjack.domain.user.exceptions.PlayerException;
import blackjack.domain.user.exceptions.PlayersException;
import blackjack.view.ErrorView;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Blackjack {
	public static void main(String[] args) {
		String playerNames = InputView.inputPlayerNames();
		Players players = preparePlayers(playerNames);
		Dealer dealer = DefaultDealer.create();
		Deck deck = prepareDeck();

		start(players, dealer, deck);

		progress(players, deck);
		progress(dealer, deck);

		finish(players, dealer);
	}

	private static Players preparePlayers(String playerNames) {
		Players players;
		do {
			players = preparePlayersIfValid(playerNames);
		} while (players == null);
		return players;
	}

	private static Players preparePlayersIfValid(String playerNames) {
		try {
			return Players.of(playerNames);
		} catch (PlayersException | PlayerException e) {
            ErrorView.print(e);
			return null;
		}
	}

	private static Deck prepareDeck() {
		Deck deck = Deck.create();
		deck.shuffle();
		return deck;
	}

	private static void start(Players players, Dealer dealer, Deck deck) {
		giveTwoCard(dealer, deck);
		giveTwoCard(players, deck);
		OutputView.printStartInfo(dealer, players);
	}

	private static void giveTwoCard(Dealer dealer, Deck deck) {
		dealer.giveCards(deck.draw(), deck.draw());
	}

	private static void giveTwoCard(Players players, Deck deck) {
		for (int i = 0; i < players.memberSize(); i++) {
			players.giveCards(i, deck.draw(), deck.draw());
		}
	}

	private static void progress(Players players, Deck deck) {
		for (Player player : players.getPlayers()) {
			progress(player, deck);
		}
	}

	private static void progress(Player player, Deck deck) {
		while (shouldProgress(player)) {
			player.giveCards(deck.draw());
			OutputView.printPlayerCard(player);
		}
	}

	private static boolean shouldProgress(Player player) {
		return player.isNotBust() && InputView.inputYesOrNo(player).isYes();
	}

	private static void progress(Dealer dealer, Deck deck) {
		while (dealer.shouldReceiveCard()) {
			dealer.giveCards(deck.draw());
			OutputView.printDealerTurn(dealer);
		}
	}

	private static void finish(Players players, Dealer dealer) {
		OutputView.printFinalInfo(dealer, players);
		Result result = Result.of(dealer, players);
		OutputView.printResult(result);
	}
}
