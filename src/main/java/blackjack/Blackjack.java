package blackjack;

import blackjack.domain.Results;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Drawable;
import blackjack.domain.card.ShuffledDeckFactory;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.DefaultDealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.exceptions.PlayerException;
import blackjack.domain.user.exceptions.PlayersException;
import blackjack.view.ErrorView;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.YesOrNo;
import blackjack.view.exceptions.YesOrNoException;

public class Blackjack {
	public static void main(String[] args) {
		String playerNames = InputView.inputPlayerNames();
		Players players = preparePlayers(playerNames);
		Dealer dealer = DefaultDealer.dealer();
		Drawable deck = prepareDeck();

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

	private static Deck prepareDeck() {
		return Deck.ofDeckFactory(new ShuffledDeckFactory());
	}

	private static void start(Players players, Dealer dealer, Drawable cards) {
		players.giveCardEachPlayer(cards);
		players.giveCardEachPlayer(cards);

		dealer.giveCards(cards.draw(2));

		OutputView.printStartInfo(dealer, players);
	}

	private static void progressPlayers(Players players, Drawable cards) {
		for (Player player : players.getPlayers()) {
			progressPlayer(player, cards);
		}
	}

	private static void progressPlayer(Player player, Drawable cards) {
		while (willProgress(player)) {
			player.giveCard(cards.draw());
			OutputView.printPlayerCard(player);
		}
	}

	private static boolean willProgress(Player player) {
		YesOrNo yesOrNo = prepareYesOrNo(player);
		return player.isNotBust() && yesOrNo.isYes();
	}

	private static YesOrNo prepareYesOrNo(Player player) {
		YesOrNo yesOrNo;
		do {
			yesOrNo = prepareYesOrNoIfValid(player);
		} while (yesOrNo == null);
		return yesOrNo;
	}

	private static YesOrNo prepareYesOrNoIfValid(Player player) {
		try {
			return YesOrNo.of(InputView.inputYesOrNo(player));
		} catch (YesOrNoException e) {
			return null;
		}
	}

	private static void progressDealer(Dealer dealer, Drawable cards) {
		while (dealer.shouldReceiveCard()) {
			dealer.giveCard(cards.draw());
			OutputView.printDealerTurn(dealer);
		}
	}

	private static void finish(Players players, Dealer dealer) {
		OutputView.printFinalInfo(dealer, players);
		Results results = Results.of(dealer, players);
		OutputView.printResult(results);
	}
}
