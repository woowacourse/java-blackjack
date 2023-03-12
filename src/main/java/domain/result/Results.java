package domain.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Dealer;
import domain.Player;

public class Results {

	private final List<PlayerResult> playerResults;

	private Results(final List<PlayerResult> playerResults) {
		this.playerResults = playerResults;
	}

	public static Results of(final Dealer dealer, final List<Player> players) {
		List<PlayerResult> playerResult = new ArrayList<>();
		for (Player player : players) {
			playerResult.add(PlayerResult.decide(player, dealer));
		}
		return new Results(playerResult);
	}

	public Result getDealerResult() {
		return DealerResult.from(playerResults);
	}

	public List<Result> getPlayerResults() {
		return Collections.unmodifiableList(playerResults);
	}
}
