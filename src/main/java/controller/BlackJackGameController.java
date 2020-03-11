package controller;

import domain.Result;
import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.User;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGameController {
	private static final int FIRST_DRAW_COUNT = 2;

	public static void run() {
		String name = InputView.inputUserNames();
		List<User> users = makeUsers(name);
		Dealer dealer = new Dealer();
		CardDeck cardDeck = new CardDeck();

		firstDraw(users, dealer, cardDeck);

		OutputView.firstDrawMessage(name, FIRST_DRAW_COUNT);
		OutputView.printOneCard(dealer);
		for (User user : users) {
			OutputView.printAllCard(user);
		}

		for (User user : users) {
			userHit(cardDeck, user);
		}
		dealerHit(dealer, cardDeck);

		OutputView.printFinalScore(dealer);
		for (User user : users) {
			OutputView.printFinalScore(user);
		}

		Map<String, Result> userResultMap = new HashMap<>();
		for (User user : users) {
			userResultMap.put(user.getName(), user.compareScore(dealer));
		}

		OutputView.printFinalResult();
		int dealerWin = 0;
		int dealerDraw = 0;
		int dealerLose = 0;
		for (Result value : userResultMap.values()) {
			if (value == Result.WIN) {
				dealerLose++;
			}
			if (value == Result.DRAW) {
				dealerDraw++;
			}
			if (value == Result.LOSE) {
				dealerWin++;
			}
		}
		OutputView.printDealerResult(dealerWin, dealerDraw, dealerLose);

		for (Map.Entry<String, Result> userResultEntry : userResultMap.entrySet()) {
			OutputView.printUserResult(userResultEntry);
		}

	}

	private static void userHit(CardDeck cardDeck, User user) {
		while (InputUtils.isHitToBoolean(InputView.inputIsHit(user))) {
			user.cardDraw(cardDeck);
			OutputView.printAllCard(user);
		}
	}

	private static void firstDraw(List<User> users, Dealer dealer, CardDeck cardDeck) {
		dealer.cardDraw(cardDeck, FIRST_DRAW_COUNT);
		for (User user : users) {
			user.cardDraw(cardDeck, FIRST_DRAW_COUNT);
		}
	}

	public static void dealerHit(Dealer dealer, CardDeck cardDeck) {
		while (dealer.calculateScore() <= 16) {
			OutputView.printDealerAdditionalCard();
			dealer.cardDraw(cardDeck);
		}
	}

	public static List<User> makeUsers(String names) {
		List<User> users = new ArrayList<>();
		String[] userNames = names.split(",");
		for (String name : userNames) {
			users.add(new User(name));
		}
		return users;
	}

}
