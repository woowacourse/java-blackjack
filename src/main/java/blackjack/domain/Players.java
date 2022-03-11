package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.jar.Attributes;

public class Players {
	private final List<Player> players;

	public Players(Names playerNames) {
		players = new ArrayList<>();
		for (Name playerName : playerNames.getNames()) {
			players.add(new Player(playerName));
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void addCardToAllPlayers(Deck deck, int times) {
		players.stream()
			.forEach(player -> player.addCards(deck, times));
	}
}
