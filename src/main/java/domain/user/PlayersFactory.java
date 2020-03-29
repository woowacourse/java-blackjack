package domain.user;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlayersFactory {

	private static final String DELIMITER = ",";

	public static Players create(String combinedName, Function<String, String> inputBettingMoney) {
		List<Player> players = Arrays.stream(combinedName.split(DELIMITER))
			.map(String::trim)
			.map(name -> new Player(name, inputBettingMoney.apply(name)))
			.collect(Collectors.toList());
		return new Players(players);
	}
}
