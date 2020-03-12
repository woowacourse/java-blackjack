package view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.Card;
import domain.Dealer;
import domain.Players;
import domain.Result;
import domain.Results;
import domain.User;

public class OutputView {
	public static void printFirstDistribute(Dealer dealer, Players players) {
		String playersName = players.getNames()
				.stream()
				.collect(Collectors.joining(", "));
		System.out.printf("%s와 %s에게 두 장의 카드를 나누었습니다.\n", dealer.getName(), playersName);
		printCards(dealer.getName(), Arrays.asList(dealer.getFirstCard()));
		players.forEach(player -> printCards(player.getName(), player.getCards()));
	}

	public static void printCards(String name, List<Card> cards) {
		System.out.printf("%s 카드: %s\n", name, cardsToString(cards));
	}

	public static void printDealerAddCard() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public static void printCardsResults(Dealer dealer, Players players) {
		printCardsResult(dealer);
		players.forEach(OutputView::printCardsResult);
	}

	private static void printCardsResult(User user) {
		System.out.printf("%s 카드: %s - 결과: %d\n", user.getName(), cardsToString(user.getCards()), user.getScore());
	}

	private static String cardsToString(List<Card> cards) {
		return cards.stream()
				.map(Card::getName)
				.collect(Collectors.joining(", "));
	}

	public static void printGameResult(Results results) {
		System.out.println("## 최종 승패");
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
