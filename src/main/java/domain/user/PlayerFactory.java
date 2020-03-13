package domain.user;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerFactory {
	private static final String DELIMITER = ",";

	public static List<User> create(String combinedName) {
		return Arrays.stream(combinedName.split(DELIMITER))
			.map(String::trim)
			.map(Player::new)
			.collect(Collectors.toList());
	}
}
