package domain.gamer;

import util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersFactory {
	public static List<Name> newNames(String input) {
		List<String> userNames = StringUtil.parseByComma(input);

		List<Name> names = userNames.stream()
				.map(Name::new)
				.collect(Collectors.toList());
		return Collections.unmodifiableList(names);
	}

	public static List<Player> of(List<Name> names, List<String> bettingMoney) {
		List<Player> players = new ArrayList<>();
		List<Money> monies = newMonies(bettingMoney);

		for (int i = 0; i < names.size(); i++) {
			players.add(new Player(names.get(i), monies.get(i)));
		}
		return Collections.unmodifiableList(players);
	}

	private static List<Money> newMonies(List<String> bettingMoney) {
		return bettingMoney.stream()
				.map(Money::new)
				.collect(Collectors.toList());
	}
}
