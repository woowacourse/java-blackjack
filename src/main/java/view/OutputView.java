package view;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

	private static final String SHOW_HAND_FORMAT = "%s카드: %s";
	private static final String SHOW_HAND_AND_BEST_SCORE_DELIMITER = " - 결과 : ";
	private static final String JOINING_DELIMITER = ", ";
	private static final String BUST_MESSAGE = "[ Bust!!! ]";
	private static final String INIT_MESSAGE_FORMAT = "\n딜러와 %s에게 2장의 나누었습니다.\n";
	private static final String MAX_SCORE_MESSAGE = "[ MAX SCORE!!! ]";
	private static final String RESULT_TITLE_MESSAGE = "\n## 최종 승패";
	private static final String DEALER_RESULT_MESSAGE_FORMAT = "딜러: %d승 %d무 %d패\n";
	private static final String PLAYER_RESULT_MESSAGE_FORMAT = "%s: %s\n";
	private static final String DEALER_DRAW_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n";
	private static final String BLACK_JACK_RESuLT_TITLE_MESSAGE = "[ BLACK JACK ]";

	public static void printInitMessage(List<String> names) {
		String namesForPrint = names.stream().collect(Collectors.joining(", "));
		System.out.printf(INIT_MESSAGE_FORMAT, namesForPrint);
	}

	private static String joinNameAndCard(String name, List<String> cards) {
		return String.format(SHOW_HAND_FORMAT, name, String.join(JOINING_DELIMITER, cards));
	}

	public static void printHand(String name, List<String> cards) {
		System.out.println(joinNameAndCard(name, cards));
	}

	public static void printHandAndScore(String name, List<String> cards, int score) {
		System.out.println(
			String.join(SHOW_HAND_AND_BEST_SCORE_DELIMITER, joinNameAndCard(name, cards), String.valueOf(score)));
	}

	public static void printBustMessage() {
		System.out.println(BUST_MESSAGE);
	}

	public static void printMaxScoreMessage() {
		System.out.println(MAX_SCORE_MESSAGE);
	}

	public static void printResultTitle() {
		System.out.println(RESULT_TITLE_MESSAGE);
	}

	public static void printDealerResult(int winCount, int drawCount, int loseCount) {
		System.out.printf(DEALER_RESULT_MESSAGE_FORMAT, winCount, drawCount, loseCount);
	}

	public static void printPlayerResult(String name, String result) {
		System.out.printf(PLAYER_RESULT_MESSAGE_FORMAT, name, result);
	}

	public static void printDealerDrawMessage() {
		System.out.println(DEALER_DRAW_MESSAGE);
	}

	public static void printBlackJackResultTitle() {
		System.out.println(BLACK_JACK_RESuLT_TITLE_MESSAGE);
	}

	public static void printErrorMessage(String message) {
		System.out.println(message);
	}
}
