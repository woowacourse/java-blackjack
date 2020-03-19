package view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.Card;
import domain.Dealer;
import domain.Name;
import domain.Players;
import domain.Profit;
import domain.Profits;
import domain.Record;
import domain.Results;
import domain.User;

public class OutputView {
	private OutputView() {
	}

	public static void printFirstDistribute(Dealer dealer, Players players) {
		String playersName = String.join(", ", players.getNames());
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
		results.getValue()
				.forEach(OutputView::printIndividualResult);
	}

	private static void printIndividualResult(Name name, Record record) {
		if (record.hasMany()) {
			System.out.printf(
					"%s: %d승 %d무 %s패\n", name.getValue(), record.getWinCount(), record.getDrawCount(),
					record.getLoseCount());
			return;
		}
		if (record.hasWin()) {
			System.out.println(name.getValue() + ": 승");
			return;
		}
		if (record.hasDraw()) {
			System.out.println(name.getValue() + ": 무");
			return;
		}
		System.out.println(name.getValue() + ": 패");
	}

	public static void printProfits(Profits profits) {
		System.out.println("## 최종 수익");
		profits.getValue().forEach(OutputView::printProfit);
	}

	private static void printProfit(Name name, Profit profit) {
		System.out.printf("%s: %.0f\n", name.getValue(), profit.getValue());
	}
}
