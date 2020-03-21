package domain.user;

import static java.util.stream.Collectors.*;
import static util.InputUtil.*;

import java.util.function.Function;

public class PlayerFactory {
	public static Players create(String combinedName, Function<String, Integer> bettingMoneyFunction) {
		return splitNames(combinedName).stream()
			.map(String::trim)
			.map(name -> Player.fromNameAndMoney(name, bettingMoneyFunction.apply(name)))
			.collect(collectingAndThen(toList(), Players::new));
	}
}
