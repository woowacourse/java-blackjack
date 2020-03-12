package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.YesOrNo;
import domain.card.CardDivider;
import domain.result.DealerResult;
import domain.result.MatchResult;
import domain.result.Result;
import domain.result.UserResult;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerFactory;
import domain.user.User;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
	private final CardDivider cardDivider;
	private final List<User> users;
	private final Dealer dealer;

	public BlackjackGame(CardDivider cardDivider) {
		users = PlayerFactory.create(InputView.inputNames());
		dealer = new Dealer();
		this.cardDivider = cardDivider;
	}

	public void run() {
		List<User> allUsers = initAllUsers();
		initCards(allUsers);
		printBlackjackUsers(allUsers);
		checkCanDraw(users, dealer);
		printUserResult(users, dealer);
		printGameResult(users, dealer);
	}

	private List<User> initAllUsers() {
		List<User> allUsers = new ArrayList<>(users);
		allUsers.add(dealer);
		return allUsers;
	}

	private void initCards(List<User> allUsers) {
		for (User user : allUsers) {
			user.addCards(Arrays.asList(cardDivider.divide(), cardDivider.divide()));
		}
		OutputView.printInitialResult(allUsers);
	}

	private void printBlackjackUsers(List<User> allUsers) {
		List<User> blackjackUsers = allUsers.stream()
			.filter(User::isBlackjack)
			.collect(Collectors.toList());
		OutputView.printBlackJackUser(blackjackUsers);
	}

	private void checkCanDraw(List<User> users, Dealer dealer) {
		if (dealer.isBlackjack()) {
			return;
		}
		drawCard(users, dealer);
	}

	private void drawCard(List<User> users, Dealer dealer) {
		drawUserCard(users);
		drawDealerCard(dealer);
	}

	private void drawUserCard(List<User> users) {
		for (User user : users) {
			if (user.isBlackjack()) {
				continue;
			}
			YesOrNo yesOrNo = new YesOrNo(InputView.inputYesORNo(user.getName()));
			while (yesOrNo.isContinue()) {
				user.addCards(Arrays.asList(cardDivider.divide()));
				OutputView.printUserCard(user);
				if (user.isBust()) {
					OutputView.printUserBust(user);
					break;
				}
				yesOrNo = new YesOrNo(InputView.inputYesORNo(user.getName()));
			}
		}
	}

	private void drawDealerCard(Dealer dealer) {
		while (dealer.isDrawable()) {
			dealer.addCards(Arrays.asList(cardDivider.divide()));
			OutputView.printDealerDraw();
		}
	}

	private void printUserResult(List<User> users, Dealer dealer) {
		List<User> allUsers = new ArrayList<>(users);
		allUsers.add(dealer);
		OutputView.printUserResult(allUsers);
	}

	private void printGameResult(List<User> users, Dealer dealer) {
		Result result = new Result(users, dealer);

		List<UserResult> userResults = users.stream()
			.map(user -> new UserResult(user, MatchResult.findMatchResult(user, dealer)))
			.collect(Collectors.toList());

		DealerResult dealerResult = new DealerResult(result.getResult());
		OutputView.printGameResult(userResults, dealerResult);
	}
}
