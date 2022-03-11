package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {
	private final List<Player> players = new ArrayList<>();
	private int playerIndex = 0;

	public PlayerRepository(List<String> playerNames) {
		addAll(playerNames);
	}

	private void addAll(List<String> playerNames) {
		playerNames.forEach(name -> players.add(new Player(name)));
	}

	public Player findPlayer(Player player) {
		return players.get(findIndex(player));
	}

	public List<Player> findAll() {
		return List.copyOf(players);
	}

	public boolean isEnd() {
		return !(playerIndex < players.size());
	}

	public void next() {
		playerIndex++;
	}

	public Player findNextPlayer() {
		Player player = players.get(playerIndex);
		return Player.copy(player);
	}

	public void saveAll(List<Player> players) {
		players.forEach(this::save);
	}

	public void save(Player player) {
		players.set(findIndex(player), player);
	}

	private int findIndex(Player player) {
		return players.indexOf(player);
	}
}
