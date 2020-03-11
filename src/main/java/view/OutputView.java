package view;

import domain.Result;
import domain.Rull;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.User;

import java.util.Map;

public class OutputView {
	private static final String STRING_FORMAT_FIRST_DRAW_MESSAGE = "딜러와 %s에게 %d장씩 나누었습니다.";
	private static final String STRING_FORMAT_FINAL_SCORE = "%s - 결과 : %s";
	private static final String STRING_FORMAT_DEALER_HIT_MESSAGE = "\n딜러는 %s이하라 한장의 카드를 더 받았습니다.\n";
	private static final String FINAL_RESULT_MESSAGE = "\n## 최종 승패";
	private static final String COLON = " : ";

	public static void firstDrawMessage(String name, int firstDrawCount) {
		System.out.println(String.format(STRING_FORMAT_FIRST_DRAW_MESSAGE, name, firstDrawCount));
	}

	public static void printOneCard(Dealer dealer) {
		System.out.println(dealer.toStringOneCard());
	}

	public static void printAllCard(User user) {
		System.out.println(user.toStringAllCard());
	}

	public static void printDealerHitMessage() {
		System.out.println(String.format(STRING_FORMAT_DEALER_HIT_MESSAGE, Rull.DEALER_HIT_SCORE));
	}

	public static void printFinalScore(Player player) {
		System.out.println(String.format(STRING_FORMAT_FINAL_SCORE,
				player.toStringAllCard(),
				player.calculateScore()));
	}

	public static void printFinalResultMessage() {
		System.out.println(FINAL_RESULT_MESSAGE);
	}

	public static void printDealerResult(Map<Result, Integer> dealerResult) {
		StringBuilder resultBuilder = new StringBuilder();
		for (Result result : dealerResult.keySet()) {
			if (dealerResult.get(result) > 0) {
				resultBuilder.append(String.valueOf(dealerResult.get(result)) + result);
			}
		}
		System.out.println(String.format("딜러 : %s", resultBuilder));
	}

	public static void printUserResult(Map<String, Result> userResult) {
		for (Map.Entry<String, Result> userResultEntry : userResult.entrySet()) {
			System.out.println(userResultEntry.getKey() + COLON + userResultEntry.getValue());
		}
	}
}
