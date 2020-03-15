package view;

import domain.Result;
import domain.ScoreType;
import domain.card.Card;
import domain.card.Cards;
import domain.ResultType;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

import static java.util.stream.Collectors.joining;

public class OutputView {
	private static final String DELIMITER = ", ";

	public static void printInitialStatus(Card dealerCard, Players players) {
		emptyLine();
		System.out.println("딜러와 " + players.getNames() + "에게 2장의 카드를 나누었습니다.");
		System.out.println("딜러: " + dealerCard);
		players.forEach(OutputView::printCardsStatusOf);
		emptyLine();
	}

	public static void printCardsStatusOf(Player player) {
		Cards cards = player.openAllCards();
		System.out.println(player + "카드: " +
			cards.toList().stream()
				.map(Card::toString)
				.collect(joining(DELIMITER)));
	}

	public static void printDealerDrawing() {
		emptyLine();
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public static void printResultStatus(Cards dealerCards, Players players) {
		emptyLine();
		System.out.println("딜러: " + dealerCards.toList().stream()
			.map(Card::toString)
			.collect(joining(DELIMITER)) +
			" - 결과: " + ScoreType.of(dealerCards).getScore(dealerCards.getPoint()));

		players.forEach(OutputView::printCardsResultOf);
	}

	private static void printCardsResultOf(Player player) {
		Cards cards = player.openAllCards();
		System.out.println(player + "카드: " +
			cards.toList().stream()
				.map(Card::toString)
				.collect(joining(DELIMITER)) +
			" - 결과: " + ScoreType.of(cards).getScore(cards.getPoint()));
	}

	public static void printResult(Dealer dealer, Players players) {
		emptyLine();
		System.out.println("## 최종 승패");
		System.out.print("딜러:");
		Result.from(dealer, players).getResults()
			.forEach((result, count) -> System.out.printf(" %d%s", count, result));
		emptyLine();
		players.forEach(player -> System.out.println(player + ": " + ResultType.from(player, dealer).getName()));
	}

	private static void emptyLine() {
		System.out.println();
	}
}
