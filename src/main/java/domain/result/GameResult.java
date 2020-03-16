package domain.result;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import util.ListUtil;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
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
		return playersResult()
				.values()
				.stream()
				.map(ResultType::reverse)
				.collect(collectingAndThen(toList(), ListUtil::countFrequency));
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
