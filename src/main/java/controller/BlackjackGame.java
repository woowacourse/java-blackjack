package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.MoreDrawResponse;
import domain.card.CardDeck;
import domain.result.DealerResult;
import domain.result.MatchCalculator;
import domain.result.MatchResult;
import domain.result.PlayerResult;
import domain.user.Dealer;
import domain.user.PlayerFactory;
import domain.user.User;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
	private final CardDeck cardDeck;
	private final List<User> players;
	private final Dealer dealer;

	public BlackjackGame(CardDeck cardDeck) {
		players = PlayerFactory.create(InputView.inputNames());
		dealer = new Dealer();
		this.cardDeck = cardDeck;
	}

	public void play() {
		List<User> allUsers = initAllUsers();
		drawInitialCards(allUsers);
		printBlackjackUsers(allUsers);
		drawOptionalCards();
		printAllUserScore();
		printGameResult();
	}

	private List<User> initAllUsers() {
		List<User> allUsers = new ArrayList<>(players);
		allUsers.add(dealer);
		return allUsers;
	}

	private void drawInitialCards(List<User> allUsers) {
		for (User user : allUsers) {
			user.addCards(cardDeck.draw(), cardDeck.draw());
		}
		OutputView.printInitialResult(allUsers);
	}

	private void printBlackjackUsers(List<User> allUsers) {
		List<User> blackjackUsers = allUsers.stream()
			.filter(User::isBlackjack)
			.collect(Collectors.toList());
		OutputView.printBlackJackUser(blackjackUsers);
	}

	private void drawOptionalCards() {
		if (dealer.isBlackjack()) {
			return;
		}
		drawCard();
	}

	private void drawCard() {
		drawPlayersCard();
		drawDealerCard();
	}

	private void drawPlayersCard() {
		for (User user : players) {
			drawPlayerCard(user);
		}
	}

	private void drawPlayerCard(User user) {
		if (user.isBlackjack()) {
			return;
		}
		while (isUserNotBust(user) && hasNeedMoreCard(user)) {
			user.addCards(cardDeck.draw());
			OutputView.printUserCard(user);
		}
	}

	private boolean isUserNotBust(User user) {
		if (!user.isBust()) {
			return true;
		}
		OutputView.printUserBust(user);
		return false;
	}

	private boolean hasNeedMoreCard(User user) {
		MoreDrawResponse moreDrawResponse = MoreDrawResponse.of(InputView.inputPlayerMoreDrawResponse(user.getName()));
		return moreDrawResponse.isMoreDraw();
	}

	private void drawDealerCard() {
		while (dealer.isDrawable()) {
			dealer.addCards(cardDeck.draw());
			OutputView.printDealerDraw();
		}
	}

	private void printAllUserScore() {
		List<User> allUsers = new ArrayList<>(players);
		allUsers.add(dealer);
		OutputView.printUserResult(allUsers);
	}

	private void printGameResult() {
		List<PlayerResult> userResults = players.stream()
			.map(user -> new PlayerResult(user, MatchResult.calculatePlayerMatchResult(user, dealer)))
			.collect(Collectors.toList());
		DealerResult dealerResult = new DealerResult(new MatchCalculator(players, dealer).getMatchResults());

		OutputView.printGameResult(userResults, dealerResult);
	}
}
