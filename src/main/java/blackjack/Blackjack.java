package blackjack;

import blackjack.domain.Result;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.ShuffledDeckFactory;
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
		Deck deck = prepareDeck();

		start(players, dealer, deck);

		progressPlayers(players, deck);
		progressDealer(dealer, deck);

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
		return Cards.ofDeckFactory(new ShuffledDeckFactory());
	}

	private static void start(Players players, Dealer dealer, Deck cards) {
		giveTwoCardDealer(dealer, cards);
		giveTwoCardEachPlayer(players, cards);
		OutputView.printStartInfo(dealer, players);
	}

	private static void giveTwoCardDealer(Dealer dealer, Deck cards) {
		dealer.giveCards(cards.draw(), cards.draw());
	}

	private static void giveTwoCardEachPlayer(Players players, Deck cards) {
		for (int i = 0; i < players.memberSize(); i++) {
			players.giveCards(i, cards.draw(), cards.draw());
		}
	}

	private static void progressPlayers(Players players, Deck cards) {
		for (Player player : players.getPlayers()) {
			progressPlayer(player, cards);
		}
	}

	private static void progressPlayer(Player player, Deck cards) {
		while (willProgress(player)) {
			player.giveCards(cards.draw());
			OutputView.printPlayerCard(player);
		}
	}

	private static boolean willProgress(Player player) {
		return player.isNotBust() && InputView.inputYesOrNo(player).isYes();
	}

	private static void progressDealer(Dealer dealer, Deck cards) {
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
