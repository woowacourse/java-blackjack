package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Players {
	private final List<Player> players;

	public Players(List<String> playerNames) {
		if (playerNames.isEmpty()) {
			throw new IllegalArgumentException("플레이어를 한명 이상 입력해야 합니다.");
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

	public void addCardToAllPlayers(Deck deck) {
		players.stream()
			.forEach(player -> player.addTwoCards(deck));
	}

	private void validateDuplicateName(List<String> playerNames) {
		if (new HashSet<String>(playerNames).size() != playerNames.size()) {
			throw new IllegalArgumentException("이름에 중복이 있으면 안됩니다.");
		}
	}
}
