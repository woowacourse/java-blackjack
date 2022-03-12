package blackjack.domain.user;

import static blackjack.domain.exceptionMessages.UserExceptionMessage.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import blackjack.domain.card.Deck;

public class Players {
	private final List<Player> players;

	public Players(final List<String> playerNames) {
		if (playerNames.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_PLAYER_EXCEPTION.getMessage());
		}
		validateDuplicateName(playerNames);
		players = new ArrayList<>();
		for (String playerName : playerNames) {
			players.add(new Player(playerName));
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void addCardToAllPlayers(final Deck deck) {
		players.stream()
			.forEach(player -> player.addTwoCards(deck));
	}

	private void validateDuplicateName(final List<String> playerNames) {
		if (new HashSet<>(playerNames).size() != playerNames.size()) {
			throw new IllegalArgumentException(DUPLICATE_PLAYER_EXCEPTION.getMessage());
		}
	}
}
