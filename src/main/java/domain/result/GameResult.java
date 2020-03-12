package domain.result;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import domain.gamer.Dealer;
import domain.gamer.Player;

public class GameResult {
	private final Map<Player, ResultType> playersResult;

	private GameResult(Map<Player, ResultType> playersResult) {
		this.playersResult = playersResult;
	}

	public static GameResult from(List<Player> players, Dealer dealer) {
		Map<Player, ResultType> playerToResult = new HashMap<>();
		for (Player player : players) {
			playerToResult.put(player, ResultType.of(player.calculateScore(), dealer.calculateScore()));
		}
		return new GameResult(playerToResult);
	}

	public TreeMap<ResultType, Integer> dealerResult() {
		List<ResultType> dealerResultType = playersResult.values()
			.stream()
			.map(ResultType::reverse)
			.collect(toList());

		return dealerResultType.stream()  //TreeMap을 만들기 위한 로직
			.collect(Collectors.toMap(result -> result, result -> Collections.frequency(dealerResultType, result),
				(v1, v2) -> v1, TreeMap::new));
	}

	public Map<Player, ResultType> getPlayersResult() {
		return Collections.unmodifiableMap(playersResult);
	}
}
