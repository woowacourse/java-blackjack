package blackjack.domain.user;

import blackjack.domain.card.Drawable;
import blackjack.domain.user.exceptions.PlayersException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public final class Players {
	private final List<Playable> players;

	private Players(List<Playable> players) {
		validatePlayersIsNotEmpty(players);
		validateDistinctNames(players);
		this.players = players;
	}

	private void validateDistinctNames(List<Playable> Players) {
		int distinctCount = (int) Players.stream()
				.map(Playable::getName)
				.distinct()
				.count();

		if (Players.size() != distinctCount) {
			throw new PlayersException("중복된 이름이 있으면 안 됩니다.");
		}
	}

	private void validatePlayersIsNotEmpty(List<Playable> players) {
		if (players.isEmpty()) {
			throw new PlayersException("플레이어는 한 명 이상이어야 합니다.");
		}
	}

	public static Players of(String playerNames) {
		if (playerNames == null) {
			return new Players(Collections.emptyList());
		}

		List<Playable> players = playerNamesToPlayableList(playerNames);
		return new Players(players);
	}

	private static List<Playable> playerNamesToPlayableList(String playerNames) {
		return Arrays.stream(playerNames.split(","))
				.map(String::trim)
				.map(Player::of)
				.collect(collectingAndThen(toList(),
						Collections::unmodifiableList));
	}

	private void giveCardEachPlayer(Drawable deck) {
		for (Playable player : players) {
			player.giveCard(deck.draw());
		}
	}

	public void giveCardEachPlayerTwice(Drawable deck) {
		giveCardEachPlayer(deck);
		giveCardEachPlayer(deck);
	}

	public int memberSize() {
		return players.size();
	}

	public List<Playable> getPlayers() {
		return Collections.unmodifiableList(players);
	}
}
