package domain.paticipant;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

import domain.card.Deck;

public class Players {
	private final List<Player> players;

	public Players(final List<Player> players) {
		this.players = new ArrayList<>(players);
	}

	public static Players from(final List<String> names) {
		return new Players(names.stream()
			.map(Player::new)
			.collect(Collectors.toList()));
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void pickCards(final Deck deck, final int pickedCardCount) {
		for (final Player player : players) {
			player.addCards(deck.pickCards(pickedCardCount));
		}
	}

	public void pickCardPlayersIfNotBust(final BooleanSupplier playerAnswer, final Deck deck, final int bustScore) {
		for (final Player player : players) {
			while (!player.isBust(bustScore) && playerAnswer.getAsBoolean()) {
				player.addCards(deck.pickCards(1));
			}
		}
	}
}
