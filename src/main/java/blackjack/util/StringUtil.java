package blackjack.util;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import blackjack.domain.card.Card;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

public class StringUtil {
	private static final String SPLIT_DELIMITER = ",";
	private static final String JOIN_DELIMITER = ", ";

	public static List<String> parsingPlayerNames(String inputPlayerNames) {
		if (Objects.isNull(inputPlayerNames)) {
			throw new NullPointerException("플레이어 입력이 존재하지 않습니다.");
		}
		return Arrays.stream(inputPlayerNames.split(SPLIT_DELIMITER))
			.map(String::trim)
			.collect(toList());
	}

	public static String joinPlayerNames(List<User> players) {
		return players.stream()
			.filter(user -> user.getClass().equals(Player.class))
			.map(User::getName)
			.collect(joining(JOIN_DELIMITER));
	}

	public static String joinCards(List<Card> cards) {
		return cards.stream()
			.map(Card::toString)
			.collect(joining(JOIN_DELIMITER));
	}
}
