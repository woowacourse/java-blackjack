package domain.gamer;

import java.util.List;
import java.util.stream.Collectors;

public class PlayersFactory {
	public static List<Player> from(List<Name> names) {
		return names.stream()
			.map(Player::new)
			.collect(Collectors.toList());
	}
}
