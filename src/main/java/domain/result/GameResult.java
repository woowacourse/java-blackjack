package domain.result;

import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.participant.Dealer;
import domain.participant.Player;

public class GameResult {
	private final Map<Player, Result> playersResult;

	private GameResult(Map<Player, Result> playersResult) {
		this.playersResult = playersResult;
	}

	public static GameResult from(List<Player> players, Dealer dealer) {
		Map<Player, Result> playerToResult = new HashMap<>();
		for (Player player : players) {
			playerToResult.put(player, Result.of(player.getScore(), dealer.getScore()));
		}
		return new GameResult(playerToResult);
	}

	public Map<Result, Long> dealerResult() {
		Map<Result, Long> result = playersResult.values()
			.stream()
			.map(Result::reverse)
			.collect(groupingBy(identity(), counting()));
		for (Map.Entry<Result, Long> resultToCount : result.entrySet()) {
			if (resultToCount.getValue() == 0) {
				result.remove(resultToCount.getKey());
			}
		}
		return Collections.unmodifiableMap(result);
	}

	public Map<Player, Result> getPlayersResult() {
		return Collections.unmodifiableMap(playersResult);
	}
}
