package blackjack;

import blackjack.domain.betting.Money;
import blackjack.domain.betting.Monies;
import blackjack.domain.betting.exceptions.MoneyException;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Drawable;
import blackjack.domain.card.ShuffledDeckFactory;
import blackjack.domain.user.*;
import blackjack.domain.user.exceptions.*;
import blackjack.view.ErrorView;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Blackjack {

	public static void main(String[] args) {
		Players players = preparePlayers();
		Monies bettingMonies = prepareMonies(players);
		Playable dealer = Dealer.dealer();
		Drawable deck = prepareDeck();

		start(players, dealer, deck);

		progressPlayers(players, deck);
		progressDealer(dealer, deck);

		Results results = finish(players, dealer);
		showGameResultMonies(results, bettingMonies);
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
		} catch (PlayersException | PlayerException | AbstractPlayerException | NameException e) {
			ErrorView.printMessage(e);
			return null;
		}
	}

	private static Monies prepareMonies(Players players) {
		List<Money> monies = new ArrayList<>();
		for (Name name : players.getNames()) {
			monies.add(prepareMoney(name));
		}
		return Monies.of(players, monies);
	}

	private static Money prepareMoney(Name name) {
		Money money;
		do {
			money = prepareMoneyIfValid(name);
		} while (money == null);
		return money;
	}

	private static Money prepareMoneyIfValid(Name name) {
		try {
			return Money.of(InputView.inputBettingMoney(name));
		} catch (MoneyException e) {
			return null;
		}
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

	private static void showGameResultMonies(Results results, Monies bettingMonies) {
		OutputView.printGameResultMonies(bettingMonies.computeGameResultMonies(results));
	}
}
