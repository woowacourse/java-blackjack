package view;

import java.util.stream.Collectors;

import domain.card.Card;
import domain.user.User;

public class OutputView {
	private static final String JOINING_DELIMITER = ", ";

	private OutputView() {
	}

	public static void printUserResult(User user) {
		System.out.printf("%s 카드: %s  - 결과: %d", user.getName(), parseCardsString(user),
			user.calculateScore());
	}

	private static String parseCardsString(User user) {
		return user.getCards().stream()
			.map(OutputView::parseCardString)
			.collect(Collectors.joining(JOINING_DELIMITER));
	}

	private static String parseCardString(Card val) {
		return val.getSymbol() + val.getScore();
	}
}
