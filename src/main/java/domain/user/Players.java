package domain.user;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class Players {
	private static final String DELIMITER = ",";

	private final List<Player> players;

	private Players(List<Player> players) {
		this.players = players;
	}

	public static Players of(List<Player> values) {
		return new Players(values);
	}

	public static Players of(String names) {
		String trimmedNames = names.trim();
		return of(Arrays.stream(trimmedNames.split(DELIMITER))
				.map(Player::new)
				.collect(Collectors.toList()));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Players players1 = (Players) o;
		return Objects.equals(players, players1.players);
	}

	@Override
	public int hashCode() {
		return Objects.hash(players);
	}
}
