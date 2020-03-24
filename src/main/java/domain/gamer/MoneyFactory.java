package domain.gamer;

import java.util.List;
import java.util.stream.Collectors;

public class MoneyFactory {
	public static List<Money> create(List<String> bettingMoney) {
		return bettingMoney.stream()
			.map(Money::of)
			.collect(Collectors.toList());
	}
}
