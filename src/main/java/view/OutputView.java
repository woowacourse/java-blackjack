package view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.Card;
import domain.Dealer;
import domain.Players;
import domain.Result;
import domain.Results;
import domain.User;

public class OutputView {
	private static final String DELIMITER = ", ";
	private static final String INIT_DISTRIBUTE_COUNT = "2";

	public static void printDistributeMessage(Players players) {
		List<String> playersName = new ArrayList<>();
		players.forEach(player -> playersName.add(player.getName()));
		System.out.println(
				"\n딜러와 " + String.join(DELIMITER, playersName) + "에게 " + INIT_DISTRIBUTE_COUNT + "장 나누었습니다.");
	}

	public static void printInitStatus(Dealer dealer, Players players) {
		printStatus(dealer.getName(), dealer.getFirstCard());
		players.forEach(player -> printStatus(player.getName(), player.getCards()));
		System.out.println();
	}

	public static void printStatus(String name, List<Card> cards) {
		System.out.printf("%s카드: %s\n", name, makeCardNames(cards));
	}

	private static String makeCardNames(List<Card> cards) {
		return cards.stream()
				.map(Card::getName)
				.collect(Collectors.joining(DELIMITER));
	}

	public static void printDealerAddCard() {
		System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public static void printUsersResult(Dealer dealer, Players players) {
		System.out.println();
		printUserResult(dealer);
		players.forEach(OutputView::printUserResult);
	}

	private static void printUserResult(User user) {
		System.out.printf("%s카드: %s - 결과: %d\n", user.getName(), makeCardNames(user.getCards()), user.getScore());
	}

	public static void printLastResult(Results results) {
		System.out.println("\n## 최종 승패");
		for (Result result : results) {
			printIndividualResult(result);
		}
	}

	private static void printIndividualResult(Result result) {
		if (result.isPlayCountMoreThanOne()) {
			System.out.printf("%s: %d승 %s패\n", result.getName(), result.getWinCount(), result.getLoseCount());
			return;
		}
		if (result.hasWin()) {
			System.out.println(result.getName() + ": 승");
			return;
		}
		System.out.println(result.getName() + ": 패");
	}
}
