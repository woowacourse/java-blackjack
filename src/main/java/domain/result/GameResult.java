package domain.result;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Gamers;
import domain.gamer.Player;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class GameResult {
	private final Map<Player, Score> playersScore;
	private final Dealer dealer;
	private final Score dealerScore;

	public GameResult(Gamers gamers) {
		this.playersScore = playersScore(gamers);
		this.dealer = gamers.getDealer();
		this.dealerScore = Score.from(dealer);
	}

	public static Map<Player, Score> playersScore(Gamers gamers) {
		Map<Player, Score> playerToScore = new HashMap<>();
		for (Player player : gamers.getPlayers()) {
			playerToScore.put(player, Score.from(player));
		}

		return playerToScore;
	}

	public Map<Player, ResultType> playersResult() {
		Map<Player, ResultType> playerToResult = new HashMap<>();
		for (Player player : playersScore.keySet()) {
			playerToResult.put(player, ResultType.of(playersScore.get(player), dealerScore));
		}

		return playerToResult;
	}

	public Map<ResultType, Integer> dealerResult() {
		List<ResultType> dealerResultType = playersResult()
				.values()
				.stream()
				.map(ResultType::reverse)
				.collect(toList());

		return dealerResultType.stream()  //TreeMap을 만들기 위한 로직
				.collect(Collectors.toMap(result -> result, result -> Collections.frequency(dealerResultType, result),
						(v1, v2) -> v1, TreeMap::new));
	}

	public Map<Gamer, Score> gamersScore() {
		Map<Gamer, Score> scores = new HashMap<>(playersScore);
		scores.put(dealer, dealerScore);

		return scores;
	}
}
