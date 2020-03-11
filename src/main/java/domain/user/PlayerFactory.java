package domain.user;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {

	public static List<User> create(String combinedName) {
		String[] names = combinedName.split(",");
		List<User> players = new ArrayList<>();
		for (String name : names) {
			players.add(new Player(name.trim()));
		}
		return players;
	}
}
