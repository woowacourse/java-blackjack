package controller;

import domain.GameResult;
import domain.Rule;
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
		Dealer dealer = new Dealer();
		List<User> users = makeUsers(userName);
		CardDeck cardDeck = new CardDeck();

		firstDraw(users, dealer, cardDeck);
		printFirstDrawCards(userName, users, dealer);

		boolean isFirstDrawBlackJack = isFirstDrawBlackJack(users, dealer);
		if (!isFirstDrawBlackJack) {
			hit(users, dealer, cardDeck);
		}

		GameResult gameResult = new GameResult(users, dealer, isFirstDrawBlackJack);
		printScore(users, dealer);
		printResult(gameResult);
	}

	public static List<User> makeUsers(String names) {
		List<User> users = new ArrayList<>();
		String[] userNames = names.split(NAME_DELIMITER);
		for (String name : userNames) {
			int bettingMoney = InputUtils.toInt(InputView.inputMoney(name));
			users.add(new User(name, bettingMoney));
		}
		return users;
	}

	private static void firstDraw(List<User> users, Dealer dealer, CardDeck cardDeck) {
		dealer.cardDraw(cardDeck.draw(Rule.FIRST_DRAW_COUNT));
		for (User user : users) {
			user.cardDraw(cardDeck.draw(Rule.FIRST_DRAW_COUNT));
		}
	}

	private static void printFirstDrawCards(String userNames, List<User> users, Dealer dealer) {
		OutputView.firstDrawMessageHead(userNames, Rule.FIRST_DRAW_COUNT);
		OutputView.printOneCard(dealer);
		for (User user : users) {
			OutputView.printAllCard(user);
		}
	}

	private static boolean isFirstDrawBlackJack(List<User> users, Dealer dealer) {
		if (dealer.isBlackJack()) {
			return true;
		}
		return users.stream().anyMatch(User::isBlackJack);
	}

	private static void hit(List<User> users, Dealer dealer, CardDeck cardDeck) {
		for (User user : users) {
			userHit(user, cardDeck);
		}
		dealerHit(dealer, cardDeck);
	}

	private static void userHit(User user, CardDeck cardDeck) {
		while (user.isPossibleAddCard() && InputUtils.isHitToBoolean(InputView.inputIsHit(user))) {
			user.cardDraw(cardDeck.draw());
			OutputView.printAllCard(user);
		}
	}

	public static void dealerHit(Dealer dealer, CardDeck cardDeck) {
		while (dealer.isHit()) {
			OutputView.printDealerHitMessage();
			dealer.cardDraw(cardDeck.draw());
		}
	}

	private static void printScore(List<User> users, Dealer dealer) {
		System.out.println();
		OutputView.printFinalScore(dealer);
		for (User user : users) {
			OutputView.printFinalScore(user);
		}
	}

	private static void printResult(GameResult gameResult) {
		OutputView.printFinalResultHeadMessage();
		OutputView.printDealerResult(gameResult);
		OutputView.printUserResult(gameResult);
	}
}
