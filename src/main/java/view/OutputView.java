package view;

import domain.Score;
import domain.card.Card;
import domain.card.Cards;
import domain.result.Result;
import domain.result.ResultType;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

import java.util.Map;

import static java.util.stream.Collectors.joining;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class OutputView {

	private static final String DELIMITER = ", ";

	public static void printInitialDistribution(Players players) {
		System.out.println("딜러와 " + players.getNames() + "에게 2장의 카드를 나누었습니다.");
	}

	public static void printInitialStatus(Card dealerCard, Players players) {
		System.out.println("딜러: " + dealerCard.toString());
		players.forEach(OutputView::printCardsStatusOf);
	}

	public static void printCardsStatusOf(Player player) {
		System.out.println(
				player.toString() +
						"카드: " +
						player.openAllCards().toList().stream()
								.map(Card::toString)
								.collect(joining(DELIMITER))
		);
	}

	public static void printDealerDraw() {
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
		System.out.println(
				player +
						"카드: " +
						player.openAllCards().toList().stream()
								.map(Card::toString)
								.collect(joining(DELIMITER)) +
						" - 결과: " +
						Score.of(player.openAllCards())
		);
	}

	public static void printResult(Result result, Players players) {
		Map<ResultType, Long> dealerResult = result.createDealerResult();
		System.out.println("## 최종 승패");
		System.out.println("딜러: " +
				dealerResult.getOrDefault(ResultType.WIN, 0L) + "승 " +
				dealerResult.getOrDefault(ResultType.DRAW, 0L) + "무 " +
				dealerResult.getOrDefault(ResultType.LOSE, 0L) + "패"
		);
		players.forEach(player ->
				System.out.println(player.toString() + ": " + result.get(player).getName())
		);
	}
}
