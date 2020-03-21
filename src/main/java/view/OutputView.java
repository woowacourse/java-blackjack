package view;

import domain.GameResult;
import domain.Money;
import domain.Rule;
import domain.player.Player;
import utils.CardPrintUtils;

import java.util.Map;

public class OutputView {
	private static final String STRING_FORMAT_FIRST_DRAW_MESSAGE = "딜러와 %s에게 %d장씩 나누었습니다.";
	private static final String STRING_FORMAT_FINAL_SCORE = "%s - 결과 : %s";
	private static final String STRING_FORMAT_DEALER_HIT_MESSAGE = "\n딜러는 %s 이하라 한장의 카드를 더 받았습니다.";
	private static final String STRING_FORMAT_DEALER_RESULT = "딜러 : %.1f";
	private static final String FINAL_RESULT_MESSAGE = "\n## 최종 수익";
	private static final String COLON = " : ";

	public static void firstDrawMessageHead(String userNames, int firstDrawCount) {
		System.out.println(String.format(STRING_FORMAT_FIRST_DRAW_MESSAGE, userNames, firstDrawCount));
	}

	public static void printOneCard(Player player) {
		System.out.println(CardPrintUtils.formatNameAndOneCard(player));
	}

	public static void printAllCard(Player player) {
		System.out.println(CardPrintUtils.formatNameAndAllCard(player));
	}

	public static void printDealerHitMessage() {
		System.out.println(String.format(STRING_FORMAT_DEALER_HIT_MESSAGE, Rule.DEALER_HIT_SCORE));
	}

	public static void printFinalScore(Player player) {
		System.out.println(String.format(STRING_FORMAT_FINAL_SCORE,
				CardPrintUtils.formatNameAndAllCard(player),
				player.calculateScore()));
	}

	public static void printFinalResultHeadMessage() {
		System.out.println(FINAL_RESULT_MESSAGE);
	}

	public static void printDealerResult(GameResult gameResult) {
		double dealerMoney = Money.calculateDealerMoney(gameResult);
		System.out.println(String.format(STRING_FORMAT_DEALER_RESULT, dealerMoney));
	}

	public static void printUserResult(GameResult gameResult) {
		Map<String, Double> userResult = gameResult.getUserResult();
		for (Map.Entry<String, Double> userResultEntry : userResult.entrySet()) {
			System.out.println(userResultEntry.getKey() + COLON + userResultEntry.getValue());
		}
	}
}
