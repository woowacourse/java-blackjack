package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

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

	public void addCardToAllPlayers(int times) {
		players.forEach(player -> player.addCards(times));
	}

	public boolean isAllPlayersBlackJackOrBust() {
		return this.players.size() == this.players.stream()
			.filter(player -> player.isBlackJack() || player.isBust())
			.count();
	}
}
