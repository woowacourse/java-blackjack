package domain.result;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Gamers;
import domain.gamer.Player;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class GameResult {
	private final Map<Gamer, Score> gamerToScore;

	public GameResult(Gamers gamers) {
		this.gamerToScore = gamers.getGamers()
				.stream()
				.collect(Collectors.toMap(Function.identity(), Score::from));
	}

	public Map<Player, ResultType> playersResult() {
		Map<Player, ResultType> playerToResult = new HashMap<>();
		Score dealerScore = gamerToScore.get(findDealer());

		for (Player player : findPlayers()) {
			playerToResult.put(player, ResultType.of(gamerToScore.get(player), dealerScore));
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
		return gamerToScore;
	}

	private List<Player> findPlayers() {
		List<Gamer> gamers = new ArrayList<>(gamerToScore.keySet());

		return Gamers.findPlayers(gamers);
	}

	private Dealer findDealer() {
		List<Gamer> gamers = new ArrayList<>(gamerToScore.keySet());

		return Gamers.findDealer(gamers);
	}
}
