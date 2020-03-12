package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.user.exception.PlayersException;

import java.util.*;
import java.util.stream.Collectors;

public class Players {
	private final List<User> players;

	private Players(List<User> players) {
		validateDuplicatedName(players);
		this.players = players;
	}

	public static Players of(String playerNames) {
		List<User> players = Arrays.stream(playerNames.split(","))
				.map(String::trim)
				.map(Player::of)
				.collect(Collectors.toUnmodifiableList());

		return new Players(players);
	}

	private void validateDuplicatedName(List<User> Players) {
		int distinctCount = (int) Players.stream()
				.map(User::getName)
				.distinct()
				.count();

		if (Players.size() != distinctCount) {
			throw new PlayersException("중복된 이름이 있으면 안 됩니다.");
		}
	}

	public Map<User, Boolean> generateResult(User dealer) {
		Map<User, Boolean> playerResults = new HashMap<>();
		Score dealerScore = dealer.calculateScore();
		for (User player : players) {
			playerResults.put(player, player.isWinner(dealerScore));
		}

		return playerResults;
	}

	public void giveCard(String name, Card card) {
		players.stream()
				.filter(Player::is)
				.findFirst()
				.orElseThrow();
	}

	public List<User> getPlayers() {
		return Collections.unmodifiableList(players);
	}
}
