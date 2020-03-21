package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Drawable;
import blackjack.domain.card.ShuffledDeckFactory;
import blackjack.domain.user.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Blackjack {

	public static void main(String[] args) {
		Players players = preparePlayers();
		Playable dealer = Dealer.dealer();
		Drawable deck = prepareDeck();

		start(players, dealer, deck);

		progressPlayers(players, deck);
		progressDealer(dealer, deck);

		Results results = finish(players, dealer);
		showGameResultMonies(results);
	}

	private static Players preparePlayers() {
		List<String> names = prepareNames();
		return Players.of(names, prepareMonies(names));
	}

	private static List<String> prepareNames() {
		return Arrays.stream(InputView.inputPlayerNames()
				.split(","))
				.map(String::trim)
				.collect(Collectors.toList());
	}

	private static List<String> prepareMonies(List<String> names) {
		List<String> monies = new ArrayList<>();
		for (String name : names) {
			monies.add(InputView.inputBettingMoney(name));
		}
		return monies;
	}

	private static Deck prepareDeck() {
		return Deck.ofDeckFactory(new ShuffledDeckFactory());
	}

	private static void start(Players players, Playable dealer, Drawable deck) {
		players.giveTwoCardsEachPlayer(deck);

		dealer.receiveCards(deck.drawTwoCards());

		OutputView.printStartInfo(dealer, players);
	}

	private static void progressPlayers(Players players, Drawable deck) {
		for (Playable player : players.getPlayers()) {
			progressPlayer(player, deck);
		}
	}

	private static void progressPlayer(Playable player, Drawable deck) {
		while (willProgress(player)) {
			player.receiveCard(deck.draw());
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
		return YesOrNo.of(InputView.inputYesOrNo(player));
	}

	private static void progressDealer(Playable dealer, Drawable deck) {
		while (dealer.canReceiveCard()) {
			dealer.receiveCard(deck.draw());
			OutputView.printDealerTurn(dealer);
		}
	}

	private static Results finish(Players players, Playable dealer) {
		OutputView.printFinalInfo(dealer, players);
		Results results = Results.of(dealer, players);
		OutputView.printResult(results);

		return results;
	}

	private static void showGameResultMonies(Results results) {
		OutputView.printGameResultMonies(results);
	}
}
