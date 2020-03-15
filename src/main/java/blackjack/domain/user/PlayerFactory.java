package blackjack.domain.user;

import static java.util.stream.Collectors.*;

import java.util.List;

public class PlayerFactory {
	public static List<Player> create(List<String> playerNames) {
		return playerNames.stream()
			.map(Player::new)
			.collect(toList());
	}
}
