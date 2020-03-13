package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.YesOrNo;
import domain.card.CardDeck;
import domain.result.DealerResult;
import domain.result.MatchCalculator;
import domain.result.MatchResult;
import domain.result.PlayerResult;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerFactory;
import domain.user.User;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
	private final CardDeck cardDeck;

	public BlackjackGame(CardDeck cardDeck) {
		this.cardDeck = cardDeck;
	}

	public void play() {
		final List<Player> players = PlayerFactory.create(InputView.inputNames());
		final Dealer dealer = new Dealer();
		drawInitialCards(players, dealer);
		drawOptionalCards(players, dealer);
		printAllUserScore(players, dealer);
		printGameResult(players, dealer);
	}

	private void drawInitialCards(List<Player> players, Dealer dealer) {
		for (Player player : players) {
			player.addCards(cardDeck.draw(), cardDeck.draw());
		}
		dealer.addCards(cardDeck.draw(), cardDeck.draw());
		OutputView.printInitialResult(players, dealer);
	}

	private void drawOptionalCards(List<Player> players, Dealer dealer) {
		if (dealer.isBlackjack()) {
			return;
		}
		drawCard(players, dealer);
	}

	private void drawCard(List<Player> players, Dealer dealer) {
		drawPlayersCard(players);
		drawDealerCard(dealer);
	}

	private void drawPlayersCard(List<Player> players) {
		for (Player player : players) {
			drawPlayerCard(player);
		}
	}

	private void drawPlayerCard(Player player) {
		while (player.isDrawable() && isYes(player)) {
			player.addCards(cardDeck.draw());
			OutputView.printUserCard(player);
		}
	}

	private boolean isYes(Player player) {
		YesOrNo yesOrNo = YesOrNo.of(InputView.inputYesOrNo(player.getName()));
		return yesOrNo.isYes();
	}

	private void drawDealerCard(Dealer dealer) {
		if (dealer.isDrawable()) {
			dealer.addCards(cardDeck.draw());
			OutputView.printDealerDraw();
		}
	}

	private void printAllUserScore(List<Player> players, Dealer dealer) {
		List<User> allUsers = new ArrayList<>(players);
		allUsers.add(dealer);
		OutputView.printUserResult(allUsers);
	}

	private void printGameResult(List<Player> players, Dealer dealer) {
		List<PlayerResult> userResults = players.stream()
			.map(user -> new PlayerResult(user, MatchResult.calculatePlayerMatchResult(user, dealer)))
			.collect(Collectors.toList());
		DealerResult dealerResult = new DealerResult(new MatchCalculator(players, dealer).getMatchResults());

		OutputView.printGameResult(userResults, dealerResult);
	}
}
