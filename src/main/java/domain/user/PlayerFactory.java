package domain.user;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayerFactory {
	private static final String DELIMITER = ",";

	public static List<Player> create(String combinedName, Function<String, Integer> bettingMoneyFunction) {
		return Arrays.stream(combinedName.split(DELIMITER))
			.map(String::trim)
			.map(name -> Player.fromNameAndMoney(name, bettingMoneyFunction.apply(name)))
			.collect(Collectors.toList());
	}
}
