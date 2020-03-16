package domain.result;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import util.ListUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class GameResult {
	private final Map<Gamer, Score> gamersScore;
	private final Map<Player, ResultType> playersResult;
	private final Map<ResultType, Integer> dealerResult;

	public GameResult(Gamers gamers) {
		this.gamersScore = calculateGamersScore(gamers);
		this.playersResult = findPlayersResult(this.gamersScore);
		this.dealerResult = findDealerResult(this.playersResult);
	}

	private Map<Gamer, Score> calculateGamersScore(Gamers gamers) {
		return gamers.getGamers()
				.stream()
				.collect(Collectors.toMap(Function.identity(), Gamer::calculateScore));
	}

	private Map<Player, ResultType> findPlayersResult(Map<Gamer, Score> gamersScore) {
		Map<Player, ResultType> playerToResult = new HashMap<>();
		Score dealerScore = gamersScore.get(findDealer());

		for (Player player : findPlayers()) {
			playerToResult.put(player, ResultType.of(gamersScore.get(player), dealerScore));
		}

		return playerToResult;
	}

	private Map<ResultType, Integer> findDealerResult(Map<Player, ResultType> playersResult) {
		return playersResult
				.values()
				.stream()
				.map(ResultType::reverse)
				.collect(collectingAndThen(toList(), ListUtil::countFrequency));
	}

	private List<Player> findPlayers() {
		List<Gamer> gamers = new ArrayList<>(gamersScore.keySet());

		return Gamers.findPlayers(gamers);
	}

	private Dealer findDealer() {
		List<Gamer> gamers = new ArrayList<>(gamersScore.keySet());

		return Gamers.findDealer(gamers);
	}

	public Map<Gamer, Score> getGamersScore() {
		return gamersScore;
	}

	public Map<Player, ResultType> getPlayersResult() {
		return playersResult;
	}

	public Map<ResultType, Integer> getDealerResult() {
		return dealerResult;
	}
}
