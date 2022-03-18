package view;

import java.util.LinkedHashMap;
import java.util.List;

import domain.participant.Participant;
import domain.result.EarningRate;
import domain.result.Result;

public class OutputView {

	private static final String SHOW_HAND_FORMAT = "%s카드: %s";
	private static final String SHOW_HAND_AND_BEST_SCORE_DELIMITER = " - 결과 : ";
	private static final String JOINING_DELIMITER = ", ";
	private static final String INIT_MESSAGE_FORMAT = "\n딜러와 %s에게 2장의 나누었습니다.\n";
	private static final String RESULT_TITLE_MESSAGE = "\n## 최종 승패";
	private static final String DEALER_RESULT_MESSAGE_FORMAT = "딜러: %d\n";
	private static final String PLAYER_RESULT_MESSAGE_FORMAT = "%s: %d\n";
	private static final String DEALER_DRAW_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n";
	private static final int FIRST_CARD_INDEX = 0;

	public static void printInitMessage(List<String> names) {
		String namesForPrint = String.join(JOINING_DELIMITER, names);
		System.out.printf(INIT_MESSAGE_FORMAT, namesForPrint);
	}

	public static void printOneHandForDealer(String dealerName, List<String> cards) {
		System.out.println(String.format(SHOW_HAND_FORMAT, dealerName, cards.get(FIRST_CARD_INDEX)));
	}

	public static void printHand(String name, List<String> cards) {
		System.out.println(joinNameAndCard(name, cards));
		System.out.println();
	}

	public static void printHandAndScore(String name, List<String> cards, int score) {
		System.out.println(
			String.join(SHOW_HAND_AND_BEST_SCORE_DELIMITER, joinNameAndCard(name, cards), String.valueOf(score)));
	}

	public static void printMessage(String message) {
		System.out.println(message);
	}

	public static void printDealerDrawMessage() {
		System.out.println(DEALER_DRAW_MESSAGE);
	}

	public static void printResult(Result result) {
		printMessage(RESULT_TITLE_MESSAGE);
		System.out.printf(DEALER_RESULT_MESSAGE_FORMAT, result.getDealerMoney());

		LinkedHashMap<Participant, EarningRate> playerResults = result.getPlayerResults();
		playerResults.entrySet()
			.forEach(entry -> System.out.printf(PLAYER_RESULT_MESSAGE_FORMAT, entry.getKey().showName(),
				(int)(entry.getKey().showBetting() * entry.getValue().getEarningRate())));
	}

	private static String joinNameAndCard(String name, List<String> cards) {
		return String.format(SHOW_HAND_FORMAT, name, String.join(JOINING_DELIMITER, cards));
	}

}
