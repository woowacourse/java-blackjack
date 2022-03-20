package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.game.MatchResult;

public class Players {

	private final List<Player> value;

	public Players(List<Player> value) {
		this.value = new ArrayList<>(value);
	}

	public boolean isAllBlackJack() {
		return value.stream()
			.allMatch(Player::isBlackJack);
	}

	public Map<Player, MatchResult> match(Dealer dealer) {
		Map<Player, MatchResult> playerMatchResult = new LinkedHashMap<>();
		for (Player player : value) {
			playerMatchResult.put(player, player.state.match(dealer.state));
		}
		return playerMatchResult;
	}

	public List<Player> getValue() {
		return Collections.unmodifiableList(value);
	}
}
