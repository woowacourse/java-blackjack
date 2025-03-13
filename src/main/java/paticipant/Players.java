package paticipant;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import card.Deck;

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

	public void pickCardPlayersIfNotBust(final Function<String, Boolean> playerAnswer, final Deck deck) {
		for (final Player player : players) {
			pickCard(playerAnswer, deck, player);
		}
	}

	private void pickCard(final Function<String, Boolean> playerAnswer, final Deck deck, final Player player) {
		while (!player.isBust() && playerAnswer.apply(player.getName())) {
			player.addCards(deck.pickCards(1));
		}
	}

	public void duelVsDealer(final Consumer<Player> duel) {
		players.forEach(duel);
	}
}
