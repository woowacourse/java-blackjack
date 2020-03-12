package blackjack.util;

import static java.util.stream.Collectors.*;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

public class StringUtil {
	private static final String DELIMITER = ", ";

	public static String joinPlayerNames(List<User> players) {
		return players.stream()
			.filter(user -> user.getClass().equals(Player.class))
			.map(User::getName)
			.collect(joining(DELIMITER));
	}

	public static String joinCards(List<Card> cards) {
		return cards.stream()
			.map(Card::toString)
			.collect(joining(DELIMITER));
	}
}
