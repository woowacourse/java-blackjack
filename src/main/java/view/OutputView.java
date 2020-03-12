package view;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.result.Result;
import domain.user.User;

public class OutputView {
	private static final String JOINING_DELIMITER = ", ";
	private static final String CARD_STRING_FORMAT = " 카드: %s  ";
	private static final String RESULT_CARD_SCORE_FORMAT = "- 결과: %d";
	private static final String NEW_LINE = System.lineSeparator();

	private OutputView() {
	}

	public static void printInitialResult(List<User> users) {
		StringBuilder builder = new StringBuilder();
		for (User user : users) {
			builder.append(user.getName());
			builder.append(String.format(CARD_STRING_FORMAT, parseCardsString(user.getInitialCard())));
			builder.append(NEW_LINE);
		}
		System.out.println(builder);
	}

	public static void printBlackJackUser(List<User> users) {
		for (User user : users) {
			System.out.printf("%s 블랙잭!%s", user.getName(), NEW_LINE);
		}
	}

	public static void printUserBust(User user) {
		System.out.printf("%s 버스트!%s", user.getName(), NEW_LINE);
	}

	public static void printUserCard(User user) {
		StringBuilder builder = new StringBuilder();
		builder.append(user.getName());
		builder.append(String.format(CARD_STRING_FORMAT, parseCardsString(user.getCards())));
		builder.append(NEW_LINE);
		System.out.println(builder);

	}

	public static void printUserResult(List<User> users) {
		StringBuilder builder = new StringBuilder();
		for (User user : users) {
			builder.append(user.getName());
			builder.append(String.format(CARD_STRING_FORMAT, parseCardsString(user.getCards())));
			builder.append(String.format(RESULT_CARD_SCORE_FORMAT, user.calculateScore()));
			builder.append(NEW_LINE);
		}
		System.out.println(builder);
	}

	public static void printDealerDraw() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	private static String parseCardsString(List<Card> cards) {
		return cards.stream()
			.map(OutputView::parseCardString)
			.collect(Collectors.joining(JOINING_DELIMITER));
	}

	private static String parseCardString(Card card) {
		return card.getTypeName() + card.getSymbol();
	}

	public static void printGameResult(Result result) {
		// List<String> result1 = result.getResult();
		// for (String s : result1) {
		// 	System.out.println(s);
		// }
	}
}
