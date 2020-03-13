package blackjack;

import blackjack.domain.Result;
import blackjack.domain.card.Cards;
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
		Dealer dealer = DefaultDealer.dealer();
		Cards cards = prepareDeck();

		start(players, dealer, cards);

		progress(players, cards);
		progress(dealer, cards);

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

	private static Cards prepareDeck() {
		Cards cards = Cards.create();
		cards.shuffle();
		return cards;
	}

	private static void start(Players players, Dealer dealer, Cards cards) {
		giveTwoCard(dealer, cards);
		giveTwoCard(players, cards);
		OutputView.printStartInfo(dealer, players);
	}

	private static void giveTwoCard(Dealer dealer, Cards cards) {
		dealer.giveCards(cards.draw(), cards.draw());
	}

	private static void giveTwoCard(Players players, Cards cards) {
		for (int i = 0; i < players.memberSize(); i++) {
			players.giveCards(i, cards.draw(), cards.draw());
		}
	}

	private static void progress(Players players, Cards cards) {
		for (Player player : players.getPlayers()) {
			progress(player, cards);
		}
	}

	private static void progress(Player player, Cards cards) {
		while (shouldProgress(player)) {
			player.giveCards(cards.draw());
			OutputView.printPlayerCard(player);
		}
	}

	private static boolean shouldProgress(Player player) {
		return player.isNotBust() && InputView.inputYesOrNo(player).isYes();
	}

	private static void progress(Dealer dealer, Cards cards) {
		while (dealer.shouldReceiveCard()) {
			dealer.giveCards(cards.draw());
			OutputView.printDealerTurn(dealer);
		}
	}

	private static void finish(Players players, Dealer dealer) {
		OutputView.printFinalInfo(dealer, players);
		Result result = Result.of(dealer, players);
		OutputView.printResult(result);
	}
}
