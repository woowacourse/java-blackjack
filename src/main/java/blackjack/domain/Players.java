package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		players.forEach(player -> player.addCards(deck, times));
	}

	public List<Player> getBustPlayers() {
		return players.stream()
			.filter(Gamer::isBust)
			.collect(Collectors.toList());
	}

	public List<Player> getNotBustPlayers() {
		return players.stream()
			.filter(player -> !player.isBust())
			.collect(Collectors.toList());
	}

	public boolean isAllPlayersBlackJackOrBust() {
		return this.players.size() == this.players.stream()
			.filter(player -> player.isBlackJack() || player.isBust())
			.count();
	}
}
