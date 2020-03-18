package domain.user;

import static util.InputUtil.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayerFactory {
	public static List<Player> create(String combinedName, Function<String, Integer> bettingMoneyFunction) {
		return splitNames(combinedName).stream()
			.map(String::trim)
			.map(name -> Player.fromNameAndMoney(name, bettingMoneyFunction.apply(name)))
			.collect(Collectors.toList());
	}
}
