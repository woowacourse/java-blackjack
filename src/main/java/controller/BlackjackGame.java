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
		drawOptionalCards(players, dealer);
		printAllUserScore(players, dealer);
		printGameResult(players, dealer);
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

	private void drawOptionalCards(List<User> player, Dealer dealer) {
		if (dealer.isBlackjack()) {
			return;
		}
		drawCard(player, dealer);
	}

	private void drawCard(List<User> player, Dealer dealer) {
		drawPlayersCard(player);
		drawDealerCard(dealer);
	}

	private void drawPlayersCard(List<User> users) {
		for (User user : users) {
			drawPlayerCard(user);
		}
	}

	private void drawPlayerCard(User user) {
		if (user.isBlackjack()) {
			return;
		}
		while (isUserNotBust(user) && isContinuousFromInput(user)) {
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

	private boolean isContinuousFromInput(User user) {
		MoreDrawResponse moreDrawResponse = MoreDrawResponse.of(InputView.inputPlayerMoreDrawResponse(user.getName()));
		return moreDrawResponse.isMoreDraw();
	}

	private void drawDealerCard(Dealer dealer) {
		while (dealer.isDrawable()) {
			dealer.addCards(cardDeck.draw());
			OutputView.printDealerDraw();
		}
	}

	private void printAllUserScore(List<User> users, Dealer dealer) {
		List<User> allUsers = new ArrayList<>(users);
		allUsers.add(dealer);
		OutputView.printUserResult(allUsers);
	}

	private void printGameResult(List<User> users, Dealer dealer) {
		List<PlayerResult> userResults = users.stream()
			.map(user -> new PlayerResult(user, MatchResult.calculatePlayerMatchResult(user, dealer)))
			.collect(Collectors.toList());
		DealerResult dealerResult = new DealerResult(new MatchCalculator(users, dealer).getMatchResults());

		OutputView.printGameResult(userResults, dealerResult);
	}
}
