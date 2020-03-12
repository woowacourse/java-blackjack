package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.Result;
import domain.YesOrNo;
import domain.card.CardDivider;
import domain.user.Dealer;
import domain.user.PlayerFactory;
import domain.user.User;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
	private final CardDivider cardDivider;

	public BlackjackGame(CardDivider cardDivider) {
		this.cardDivider = cardDivider;
	}

	public void run() {
		List<User> users = PlayerFactory.create(InputView.inputNames());
		Dealer dealer = new Dealer();
		initCards(users, dealer);
		printBlackjackUsers(users, dealer);
		checkCanDraw(users, dealer);
		printUserResult(users, dealer);
	}

	private void initCards(List<User> users, Dealer dealer) {
		for (User user : users) {
			user.addCards(Arrays.asList(cardDivider.divide(), cardDivider.divide()));
		}
		dealer.addCards(Arrays.asList(cardDivider.divide(), cardDivider.divide()));

		List<User> allUsers = new ArrayList<>(users);
		allUsers.add(dealer);
		OutputView.printInitialResult(allUsers);
	}

	private void printBlackjackUsers(List<User> users, Dealer dealer) {
		List<User> allUsers = new ArrayList<>(users);
		allUsers.add(dealer);

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
		OutputView.printGameResult(result);
	}
}
