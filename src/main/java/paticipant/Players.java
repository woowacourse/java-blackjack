package paticipant;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import card.Deck;

public class Players {
	private static final int ADDITION_PLAYER_PICK_CARD_COUNT = 1;
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

	public void pickCardPlayersIfNotBust(
		final Function<String, Boolean> playerAnswer,
		final Consumer<Player> printPlayerCard,
		final Deck deck
	) {
		for (final Player player : players) {
			while (canPlayerPickCard(playerAnswer, player)) {
				player.addCards(deck.pickCards(ADDITION_PLAYER_PICK_CARD_COUNT));
				printPlayerCard.accept(player);
			}
		}
	}

	private boolean canPlayerPickCard(Function<String, Boolean> playerAnswer, Player player) {
		return !player.isBust() && playerAnswer.apply(player.getName());
	}

	public void duelVsDealer(final Consumer<Player> duel) {
		players.forEach(duel);
	}
}
