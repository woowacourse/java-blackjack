package blackjack.domain.game;

import blackjack.domain.role.Role;
import java.util.HashMap;
import java.util.Map;

public class Compete {

	private final Map<String, Outcome> competeResults;

	public Compete() {
		this.competeResults = new HashMap<>();
	}

	public void judgeCompete(final Role player, final Role dealer) {
		Outcome result = createOutcome(player, dealer);
		competeResults.put(player.getName(), result);
	}

	private Outcome createOutcome(final Role player, final Role dealer) {
		if (player.isBlackJack() || dealer.isBlackJack()) {
			return Outcome.ofBlackJack(player.isBlackJack(), dealer.isBlackJack());
		}
		if (player.isBust()) {
			return Outcome.DEFEAT;
		}
		if (dealer.isBust()) {
			return Outcome.VICTORY;
		}
		return Outcome.of(player.calculateFinalScore(), dealer.calculateFinalScore());
	}

	public Outcome getPlayerCompeteResults(final Role player) {
		return competeResults.get(player.getName());
	}
}
