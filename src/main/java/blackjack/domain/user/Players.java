package blackjack.domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import blackjack.domain.card.Deck;

public class Players {
	public static final String EMPTY_PLAYER_EXCEPTION = "플레이어를 한명 이상 입력해야 합니다.";
	public static final String DUPLICATE_PLAYER_EXCEPTION = "이름에 중복이 있으면 안됩니다.";
	private final List<Player> players;

	public Players(final List<String> playerNames) {
		validateNoPlayer(playerNames);
		validateDuplicateName(playerNames);
		players = new ArrayList<>();
		for (String playerName : playerNames) {
			players.add(new Player(playerName));
		}
	}

	private void validateNoPlayer(List<String> playerNames) {
		if (playerNames.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_PLAYER_EXCEPTION);
		}
	}

	private void validateDuplicateName(final List<String> playerNames) {
		if (new HashSet<>(playerNames).size() != playerNames.size()) {
			throw new IllegalArgumentException(DUPLICATE_PLAYER_EXCEPTION);
		}
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	public void addCardToAllPlayers(final Deck deck) {
		players.stream()
			.forEach(player -> player.addTwoCards(deck));
	}
}
