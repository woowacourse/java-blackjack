package blackjack.domain.user;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;

public class PlayerFactory {
	private static final String DELIMITER = ",";

	public static List<Player> create(String playerNames) {
		return Arrays.stream(playerNames.split(DELIMITER))
			.map(Player::new)
			.collect(toList());
	}
}
