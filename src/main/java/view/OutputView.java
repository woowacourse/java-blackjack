package view;

import java.util.List;
import java.util.stream.Collectors;

import domain.participant.Name;
import domain.participant.ParticipantInfo;

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
	private static final int LIMIT_TO_NOT_BUST_SCORE = 21;
	private static final int FIRST_CARD_INDEX = 0;

	public static void printInitMessage(List<Name> names) {
		List<String> nameInfo = names.stream().map(Name::getName).collect(Collectors.toList());
		String namesForPrint = nameInfo.stream().collect(Collectors.joining(", "));
		System.out.printf(INIT_MESSAGE_FORMAT, namesForPrint);
	}

	public static void printOneHandForDealer(ParticipantInfo participantInfo) {
		System.out.println(String.format(SHOW_HAND_FORMAT, participantInfo.getName().getName(),
			getCardInfo(participantInfo).get(FIRST_CARD_INDEX)));
	}

	public static void printHand(ParticipantInfo participantInfo) {
		System.out.println(joinNameAndCard(participantInfo));
		int score = participantInfo.getHand().getScore();

		if (score > LIMIT_TO_NOT_BUST_SCORE) {
			printMessage(BUST_MESSAGE);
		}

		if (score == LIMIT_TO_NOT_BUST_SCORE) {
			printMessage(MAX_SCORE_MESSAGE);
		}
	}

	public static void printHandAndScore(ParticipantInfo participantInfo) {
		System.out.println(
			String.join(SHOW_HAND_AND_BEST_SCORE_DELIMITER, joinNameAndCard(participantInfo),
				String.valueOf(participantInfo.getHand().getScore())));
	}

	private static String joinNameAndCard(ParticipantInfo participantInfo) {
		List<String> cardInfo = getCardInfo(participantInfo);

		return String.format(SHOW_HAND_FORMAT, participantInfo.getName().getName(),
			String.join(JOINING_DELIMITER, cardInfo));
	}

	private static List<String> getCardInfo(ParticipantInfo participantInfo) {
		List<String> cardInfo = participantInfo.getHand().getCards().stream()
			.map(card -> String.join("", List.of(card.getRank().getRank(), card.getSuit().getSuit())))
			.collect(Collectors.toList());
		return cardInfo;
	}

	public static void printDealerResult(int winCount, int drawCount, int loseCount) {
		printMessage(RESULT_TITLE_MESSAGE);
		System.out.printf(DEALER_RESULT_MESSAGE_FORMAT, winCount, drawCount, loseCount);
	}

	public static void printPlayerResult(String name, String result) {
		System.out.printf(PLAYER_RESULT_MESSAGE_FORMAT, name, result);
	}

	private static void printMessage(String message) {
		System.out.println(message);
	}

	public static void printDealerDrawMessage() {
		System.out.println(DEALER_DRAW_MESSAGE);
	}

	public static void printErrorMessage(String message) {
		System.out.println(message);
	}

}
