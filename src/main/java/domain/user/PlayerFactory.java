package domain.user;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {

	private static final String DELIMITER = ",";

	public static List<User> create(String combinedName) {
		String[] names = combinedName.split(DELIMITER);
		List<User> players = new ArrayList<>();
		for (String name : names) {
			players.add(new Player(name.trim()));
		}
		return players;
	}
}
