package blackjack.domain.user;

import blackjack.domain.card.Drawable;
import blackjack.domain.user.exceptions.PlayersException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Players {
	private final List<Playable> players;

	private Players(List<Playable> players) {
		validatePlayersIsNotEmpty(players);
		validateDistinctNames(players);
		this.players = players;
	}

	private void validateDistinctNames(List<Playable> Players) {
		int distinctCount = (int) Players.stream()
				.map((player -> player.getName().getString()))
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

	public static Players of(List<String> playerNames, List<String> monies) {
		List<Playable> players = new ArrayList<>();
		for (int i = 0; i < playerNames.size(); i++) {
			players.add(Player.of(new Name(playerNames.get(i)), Money.of(monies.get(i))));
		}

		return new Players(players);
	}

	public void giveTwoCardsEachPlayer(Drawable deck) {
		for (Playable player : players) {
			player.receiveCards(deck.drawTwoCards());
		}
	}

	public List<Playable> getPlayers() {
		return Collections.unmodifiableList(players);
	}

}
