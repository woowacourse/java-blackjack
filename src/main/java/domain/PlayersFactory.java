package domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayersFactory {
	private static final String NAME_DELIMITER = ",";

	private PlayersFactory() {
	}

	public static Players create(String userInput) {
		return Arrays.stream(userInput.split(NAME_DELIMITER))
				.map(Player::new)
				.collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
	}
}
