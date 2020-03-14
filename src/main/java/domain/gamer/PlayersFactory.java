package domain.gamer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import util.StringUtil;

public class PlayersFactory {
	public static List<Player> newPlayers(String input) {
		List<String> userNames = StringUtil.parseByComma(input);

		List<Player> players = userNames.stream()
			.map(Player::new)
			.collect(Collectors.toList());
		return Collections.unmodifiableList(players);
	}
}