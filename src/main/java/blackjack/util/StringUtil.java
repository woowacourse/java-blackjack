package blackjack.util;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

import blackjack.domain.card.Card;
import blackjack.domain.result.ResultType;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

public class StringUtil {
	private static final String DELIMITER = ", ";
	private static final String SPACE = " ";

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

	public static String joinDealerResult(Map<ResultType, Long> dealerResult) {
		return dealerResult.entrySet().stream()
			.map(entry -> {
				long count = entry.getValue();
				ResultType resultType = entry.getKey();
				return count + resultType.getAlias();
			})
			.collect(joining(SPACE));
	}
}
