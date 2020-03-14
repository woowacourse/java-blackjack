package view;

import domain.card.Card;
import domain.card.Cards;
import domain.result.Result;
import domain.result.ResultType;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

import java.util.Map;

import static java.util.stream.Collectors.joining;

public class OutputView {

	private static final String DELIMITER = ", ";

	private OutputView() {}

	public static void printInitialStatus(Card dealerCard, Players players) {
		emptyLine();
		System.out.println(String.format("딜러와 %s에게 2장의 카드를 나누었습니다.", players.getNames()));
		System.out.println(String.format("딜러: %s", dealerCard));
		players.forEach(OutputView::printCardsStatusOf);
		emptyLine();
	}

	public static void printCardsStatusOf(Player player) {
		Cards cards = player.openAllCards();

		System.out.println(String.format("%s 카드: %s",
				player,
				cards.toList().stream()
						.map(Card::toString)
						.collect(joining(DELIMITER))));
	}

	public static void printDealerDrawing() {
		emptyLine();
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public static void printResultStatus(Dealer dealer, Players players) {
		System.out.println(String.format("딜러: %s -결과: %d",
				dealer.openAllCards().toList().stream()
						.map(Card::toString)
						.collect(joining(DELIMITER)),
				dealer.calculateScore()));

		players.forEach(OutputView::printCardsResultOf);
	}

	private static void printCardsResultOf(Player player) {
		Cards cards = player.openAllCards();

		System.out.println(String.format("%s 카드: %s -결과: %d",
				player,
				cards.toList().stream()
						.map(Card::toString)
						.collect(joining(DELIMITER)),
				player.calculateScore()));
	}

	public static void printTotalResult(Result result, Players players) {
		Map<ResultType, Long> dealerResult = result.createDealerResult();

		emptyLine();
		System.out.println("## 최종 승패");
		System.out.println(String.format("딜러: %s승 %s무 %s패",
				dealerResult.getOrDefault(ResultType.WIN, 0L),
				dealerResult.getOrDefault(ResultType.DRAW, 0L),
				dealerResult.getOrDefault(ResultType.LOSE, 0L)));
		players.forEach(player ->
				System.out.println(String.format("%s: %s",
						player,
						result.get(player).getOutputContent())));
	}

	private static void emptyLine() {
		System.out.println();
	}
}
