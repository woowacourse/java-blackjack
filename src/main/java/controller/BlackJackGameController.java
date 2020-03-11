package controller;

import domain.GameResult;
import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.User;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

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

		GameResult gameResult = new GameResult(users, dealer);

		OutputView.printFinalResult();
		OutputView.printDealerResult(gameResult.getDealerResult());
		OutputView.printUserResult(gameResult.getUserResult());
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
