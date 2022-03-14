package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.strategy.NumberGenerator;

public class Players {
	private static final String DUPLICATED_ERROR = "[ERROR] 이름은 중복될 수 없습니다.";
	private static final String NO_PLAYER_ERROR = "[ERROR] 플레이어는 1명 이상이여야 합니다.";

	private final List<Player> players = new ArrayList<>();
	private int playerIndex = 0;

	public Players(List<String> playerNames) {
		validateNames(playerNames);
		playerNames.forEach(name -> players.add(new Player(name)));
	}

	private void validateNames(List<String> playerNames) {
		if (playerNames.size() == 0) {
			throw new IllegalArgumentException(NO_PLAYER_ERROR);
		}

		List<String> duplicatedChecker = playerNames.stream().distinct().collect(Collectors.toList());
		if (duplicatedChecker.size() != playerNames.size()) {
			throw new IllegalArgumentException(DUPLICATED_ERROR);
		}
	}

	public Player findPlayer(Player player) {
		return players.get(findIndex(player));
	}

	public List<Player> getPlayers() {
		return List.copyOf(players);
	}

	public boolean isEnd() {
		return !(playerIndex < players.size());
	}

	public boolean isPlayerBurst(Player player) {
		return findPlayer(player).isBurst();
	}

	public void next() {
		playerIndex++;
	}

	public Player findNextPlayer() {
		Player player = players.get(playerIndex);
		return Player.copy(player);
	}

	public void addCards(Dealer dealer, NumberGenerator numberGenerator) {
		players.forEach(player -> player
			.addCard(dealer.handOutCard(numberGenerator)));
	}

	public void addCard(Dealer dealer, Player player, NumberGenerator numberGenerator) {
		player.addCard(dealer.handOutCard(numberGenerator));
		players.set(findIndex(player), player);
	}

	private int findIndex(Player player) {
		return players.indexOf(player);
	}
}
