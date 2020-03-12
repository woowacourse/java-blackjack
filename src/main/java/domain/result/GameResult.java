package domain.result;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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

	public TreeMap<Result, Integer> dealerResult() {
		List<Result> dealerResult = playersResult.values()
			.stream()
			.map(Result::reverse)
			.collect(toList());

		return dealerResult.stream()  //TreeMap을 만들기 위한 로직
			.collect(Collectors.toMap(result -> result, result -> Collections.frequency(dealerResult, result),
				(v1, v2) -> v1, TreeMap::new));
	}

	public Map<Player, Result> getPlayersResult() {
		return Collections.unmodifiableMap(playersResult);
	}
}
