package domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.RuleChecker;
import domain.card.CardDivider;

public class Players {
	private final List<Player> players;

	public Players(List<Player> players) {
		this.players = new ArrayList<>(players);
	}

	public void drawFirst(CardDivider cardDivider) {
		players.forEach(player -> player.drawFirst(cardDivider));
	}

	public List<User> findByBlackjack(RuleChecker ruleChecker) {
		return players.stream()
			.filter(ruleChecker::isBlackjack)
			.collect(Collectors.toList());
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}
}
