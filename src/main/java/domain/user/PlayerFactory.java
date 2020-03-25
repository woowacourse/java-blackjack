package domain.user;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayerFactory {

	private static final String DELIMITER = ",";

	public static List<Player> create(String combinedName, Function<String, String> inputBettingMoney) {
		return Arrays.stream(combinedName.split(DELIMITER))
			.map(String::trim)
			.map(name -> new Player(name, inputBettingMoney.apply(name)))
			.collect(Collectors.toList());
	}
}
