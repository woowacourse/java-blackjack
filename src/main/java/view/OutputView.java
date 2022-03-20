package view;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public class OutputView {

	private static final String SHOW_HAND_FORMAT = "%s카드: %s";
	private static final String SHOW_HAND_AND_BEST_SCORE_DELIMITER = " - 결과 : ";
	private static final String JOINING_DELIMITER = ", ";
	private static final String INIT_MESSAGE_FORMAT = "\n딜러와 %s에게 2장의 나누었습니다.\n";
	private static final String RESULT_TITLE_MESSAGE = "\n## 최종 승패";
	private static final String RESULT_MESSAGE_FORMAT = "%s: %d\n";
	private static final String DEALER_DRAW_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n";
	private static final int FIRST_CARD_INDEX = 0;

	public static void printInitMessage(List<String> names) {
		String namesForPrint = String.join(JOINING_DELIMITER, names);
		System.out.printf(INIT_MESSAGE_FORMAT, namesForPrint);
	}

	public static void printOneHandForDealer(String dealerName, List<Card> cards) {
		System.out.println(String.format(SHOW_HAND_FORMAT, dealerName, getCardInfo(cards).get(FIRST_CARD_INDEX)));
	}

	public static void printHand(String name, List<Card> cards) {
		System.out.println(joinNameAndCard(name, getCardInfo(cards)));
		System.out.println();
	}

	public static void printHandAndScore(String name, List<Card> cards, int score) {
		System.out.println(
			String.join(SHOW_HAND_AND_BEST_SCORE_DELIMITER, joinNameAndCard(name, getCardInfo(cards)),
				String.valueOf(score)));
	}

	public static void printMessage(String message) {
		System.out.println(message);
	}

	public static void printDealerDrawMessage() {
		System.out.println(DEALER_DRAW_MESSAGE);
	}

	public static void printResult(String name, int money) {
		System.out.printf(RESULT_MESSAGE_FORMAT, name, money);
	}

	public static void printEndMessage() {
		printMessage(RESULT_TITLE_MESSAGE);
	}

	private static String joinNameAndCard(String name, List<String> cards) {
		return String.format(SHOW_HAND_FORMAT, name, String.join(JOINING_DELIMITER, cards));
	}

	private static List<String> getCardInfo(List<Card> cards) {
		return cards.stream()
			.map(card -> card.getDenomination() + card.getSuit())
			.collect(Collectors.toList());
	}
}
