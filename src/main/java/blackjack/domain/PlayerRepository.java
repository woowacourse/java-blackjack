package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {
	private List<Player> players;
	private int playerIndex;

	public PlayerRepository() {
		this.players = new ArrayList<>();
		this.playerIndex = 0;
	}

	public void addAll(List<String> playerNames) {
		playerNames.forEach(name -> players.add(new Player(name)));
	}

	public Player findPlayer(Player player) {
		int index = players.indexOf(player);
		return players.get(index);
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

	public Player getPlayer() {
		Player player = players.get(playerIndex);
		return Player.copy(player);
	}

	public int size() {
		return players.size();
	}

	public void saveAll(List<Player> players) {
		players.forEach(this::save);
	}

	public void save(Player player) {
		int index = players.indexOf(player);
		players.set(index, player);
	}
}
