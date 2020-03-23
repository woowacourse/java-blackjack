package domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import domain.card.CardDeck;

public class Players implements Iterable<Player> {
	private static final String NULL_OR_EMPTY_PLAYER_LIST_EXCEPTION_MESSAGE = "플레이어 목록은 null 이거나 비어있을 수 없습니다.";

	private final List<Player> players;

	public Players(List<Player> players) {
		validateNullAndEmpty(players);
		this.players = Collections.unmodifiableList(new ArrayList<>(players));
	}

	private void validateNullAndEmpty(List<Player> players) {
		if (Objects.isNull(players) || players.isEmpty()) {
			throw new IllegalArgumentException(NULL_OR_EMPTY_PLAYER_LIST_EXCEPTION_MESSAGE);
		}
	}

	public void drawFirstTime(CardDeck cardDeck) {
		for (Player player : players) {
			player.drawFirstTime(cardDeck);
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public Iterator<Player> iterator() {
		return players.iterator();
	}
}
