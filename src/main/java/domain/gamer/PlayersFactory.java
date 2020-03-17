package domain.gamer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayersFactory {
	public static List<Player> from(List<Name> names, List<Money> bettingMoney) {
		List<Player> players = new ArrayList<>();
		for(int i =0; i<names.size(); i++) {
			players.add(new Player(names.get(i), bettingMoney.get(i)));
		}
		return Collections.unmodifiableList(players);
	}
}
