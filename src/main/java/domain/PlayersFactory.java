package domain;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PlayersFactory {
	private PlayersFactory() {
	}

	private static final String NAME_DELIMITER = ",";

	public static Players create(String userInput) {
		return Arrays.stream(userInput.split(NAME_DELIMITER))
				.map(Player::new)
				.collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
	}
}
