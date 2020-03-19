package domain.user;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Users implements Iterable<User> {
	private static final int MAX_PLAYERS_COUNT = 8;
	private static final int MIN_PLAYERS_COUNT = 2;
	private static final String SPLIT_DELIMITER = ",";

	private final List<User> users;

	private Users(List<User> players, User dealer) {
		players.add(dealer);
		if (players.size() > MAX_PLAYERS_COUNT) {
			throw new IllegalArgumentException("블랙잭의 최대 인원은 8명입니다.");
		}
		if (players.size() < MIN_PLAYERS_COUNT) {
			throw new IllegalArgumentException("블랙잭의 최소 인원은 2명입니다.");
		}
		this.users = players;
	}

	public static Users of(List<User> players, User dealer) {
		return new Users(players, dealer);
	}

	public static Users of(String playerNames, User dealer) {
		String trimmedNames = playerNames.trim();
		return of(Arrays.stream(trimmedNames.split(SPLIT_DELIMITER))
			.map(Player::new)
			.collect(Collectors.toList()), dealer);
	}

	public List<Player> getPlayers() {
		return users.stream()
			.filter(user -> user.getClass() == Player.class)
			.map(user -> (Player) user)
			.collect(Collectors.toList());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Users users1 = (Users)o;
		return Objects.equals(users, users1.users);
	}

	@Override
	public int hashCode() {
		return Objects.hash(users);
	}

	@Override
	public Iterator<User> iterator() {
		return users.iterator();
	}
}
