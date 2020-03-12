package controller;

import domain.GameResult;
import domain.Rull;
import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.User;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGameController {
	private static final String NAME_DELIMITER = ",";

	public static void run() {
		String userName = InputView.inputUserNames();
		List<User> users = makeUsers(userName);
		Dealer dealer = new Dealer();
		CardDeck cardDeck = new CardDeck();

		firstDraw(users, dealer, cardDeck);
		printFirstDrawCards(userName, users, dealer);

		hit(users, dealer, cardDeck);

		printScore(users, dealer);

		GameResult gameResult = new GameResult(users, dealer);
		printResult(gameResult);
	}

	public static List<User> makeUsers(String names) {
		List<User> users = new ArrayList<>();
		String[] userNames = names.split(NAME_DELIMITER);
		for (String name : userNames) {
			users.add(new User(name));
		}
		return users;
	}

	private static void firstDraw(List<User> users, Dealer dealer, CardDeck cardDeck) {
		dealer.cardDraw(cardDeck.draw(Rull.FIRST_DRAW_COUNT));
		for (User user : users) {
			user.cardDraw(cardDeck.draw(Rull.FIRST_DRAW_COUNT));
		}
	}

	private static void printFirstDrawCards(String name, List<User> users, Dealer dealer) {
		OutputView.firstDrawMessage(name, Rull.FIRST_DRAW_COUNT);
		OutputView.printOneCard(dealer);
		for (User user : users) {
			OutputView.printAllCard(user);
		}
	}

	private static void hit(List<User> users, Dealer dealer, CardDeck cardDeck) {
		for (User user : users) {
			userHit(user, cardDeck);
		}
		dealerHit(dealer, cardDeck);
	}

	private static void userHit(User user, CardDeck cardDeck) {
		while (InputUtils.isHitToBoolean(InputView.inputIsHit(user))) {
			user.cardDraw(cardDeck.draw());
			OutputView.printAllCard(user);
		}
	}

	public static void dealerHit(Dealer dealer, CardDeck cardDeck) {
		while (dealer.calculateScore() <= Rull.DEALER_HIT_SCORE) {
			OutputView.printDealerHitMessage();
			dealer.cardDraw(cardDeck.draw());
		}
	}

	private static void printScore(List<User> users, Dealer dealer) {
		OutputView.printFinalScore(dealer);
		for (User user : users) {
			OutputView.printFinalScore(user);
		}
	}

	private static void printResult(GameResult gameResult) {
		OutputView.printFinalResultMessage();
		OutputView.printDealerResult(gameResult);
		OutputView.printUserResult(gameResult);
	}
}
