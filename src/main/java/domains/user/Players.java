package domains.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Players implements Iterable<Player> {

	private List<Player> players;

	public Players(List<Player> players) {
		this.players = players;
	}

	public Players() {
		this(new ArrayList<>());
	}

	public void add(Player player) {
		this.players.add(player);
	}

	@Override
	public Iterator<Player> iterator() {
		return players.iterator();
	}
}
