package view;

import domain.Score;
import domain.card.Card;
import domain.card.Cards;
import domain.result.Result;
import domain.result.ResultType;
import domain.user.Player;
import domain.user.Players;

import java.util.Map;

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

		System.out.println(player +
				"카드: " +
				cards.toList().stream()
						.map(Card::toString)
						.collect(joining(DELIMITER))
		);
	}

	public static void printDealerDrawing() {
		emptyLine();
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public static void printResultStatus(Cards dealerCards, Players players) {
		System.out.println("딜러: " +
				dealerCards.toList().stream()
						.map(Card::toString)
						.collect(joining(DELIMITER)) +
				" - 결과: " +
				Score.of(dealerCards));

		players.forEach(OutputView::printCardsResultOf);
	}

	private static void printCardsResultOf(Player player) {
		Cards cards = player.openAllCards();

		System.out.println(
				player +
						"카드: " +
						cards.toList().stream()
								.map(Card::toString)
								.collect(joining(DELIMITER)) +
						" - 결과: " +
						Score.of(player.openAllCards())
		);
	}

	public static void printTotalResult(Result result, Players players) {
		Map<ResultType, Long> dealerResult = result.createDealerResult();

		emptyLine();
		System.out.println("## 최종 승패");
		System.out.println("딜러: " +
				dealerResult.getOrDefault(ResultType.WIN, 0L) + "승 " +
				dealerResult.getOrDefault(ResultType.DRAW, 0L) + "무 " +
				dealerResult.getOrDefault(ResultType.LOSE, 0L) + "패"
		);
		players.forEach(player ->
				System.out.println(player + ": " + result.get(player).getName())
		);
	}

	private static void emptyLine() {
		System.out.println();
	}
}
