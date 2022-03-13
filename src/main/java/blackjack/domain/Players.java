package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.strategy.NumberGenerator;

public class Players {
	private final List<Player> players = new ArrayList<>();
	private int playerIndex = 0;

	public Players(List<String> playerNames) {
		playerNames.forEach(name -> players.add(new Player(name)));
	}

	public Player findPlayer(Player player) {
		return players.get(findIndex(player));
	}

	public List<Player> getPlayers() {
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

	public void addCards(Dealer dealer, NumberGenerator numberGenerator) {
		players.forEach(player -> player
			.addCard(dealer.handOutCard(numberGenerator)));
	}

	public void addCard(Dealer dealer, Player player, NumberGenerator numberGenerator) {
		player.addCard(dealer.handOutCard(numberGenerator));
		players.set(findIndex(player), player);
	}

	private int findIndex(Player player) {
		return players.indexOf(player);
	}
}
