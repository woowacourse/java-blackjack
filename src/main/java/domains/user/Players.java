package domains.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domains.card.Deck;
import domains.user.name.PlayerName;
import domains.user.name.PlayerNames;

public class Players implements Iterable<Player> {
	private List<Player> players;

	public Players(List<Player> players) {
		this.players = players;
	}

	public Players(String inputPlayerNames, Deck deck) {
		this.players = new ArrayList<>();
		PlayerNames playerNames = new PlayerNames(inputPlayerNames);
		for (PlayerName name : playerNames) {
			players.add(new Player(name, deck));
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public Iterator<Player> iterator() {
		return players.iterator();
	}
}
