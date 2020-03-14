package domain.result;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;

public class GameResult {
	private final Map<Player, ResultType> playersResult;
	private final Map<Gamer, Score> scores;

	private GameResult(Map<Player, ResultType> playersResult, Map<Gamer, Score> scores) {
		this.playersResult = playersResult;
		this.scores = scores;
	}

	public static GameResult of(List<Player> players, Dealer dealer) {
		Map<Player, ResultType> playerToResult = new HashMap<>();
		Map<Gamer, Score> scores = new LinkedHashMap<>();

		scores.put(dealer, dealer.calculateScore());
		for (Player player : players) {
			playerToResult.put(player, ResultType.of(player.calculateScore(), dealer.calculateScore()));
			scores.put(player, player.calculateScore());
		}
		return new GameResult(playerToResult, scores);
	}

	public Map<ResultType, Integer> dealerResult() {
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

	public Map<Gamer, Score> getScores() {
		return Collections.unmodifiableMap(scores);
	}
}
