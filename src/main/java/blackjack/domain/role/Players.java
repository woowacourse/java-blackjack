package blackjack.domain.role;

import blackjack.domain.game.Compete;
import blackjack.domain.game.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Players {

	private static final int PLAYER_DISTRIBUTE_CARD_COUNT = 2;
	
	private final List<Role> players;

	public Players(final List<Role> players) {
		this.players = players;
	}

	public Role getPlayerByName(final String name) {
		return players.stream()
				.filter(player -> player.getName().equals(name))
				.findFirst()
				.orElseThrow(NoSuchElementException::new);
	}

	public PlayerTurns getPlayerTurn() {
		return new PlayerTurns(players);
	}

	public List<Role> distributeCard(final Deck deck) {
		for (Role player : players) {
			playerDistributeCard(player, deck);
		}
		return players;
	}

	private void playerDistributeCard(final Role player, final Deck deck) {
		for (int i = 0; i < PLAYER_DISTRIBUTE_CARD_COUNT; i++) {
			player.draw(deck.draw());
		}
	}

	public List<Role> getPlayers() {
		return new ArrayList<>(players);
	}

	public Compete competeToDealer(final Role dealer) {
		final Compete compete = new Compete();
		for (Role player : players) {
			compete.judgeCompete(player, dealer);
		}
		return compete;
	}
}
