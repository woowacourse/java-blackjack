package domain.result;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Gamers;
import domain.gamer.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameResult {
	private final Map<Gamer, Score> gamersScore;
	private final Map<Player, Profit> playersToProfit;
	private final Profit dealerResult;

	public GameResult(Gamers gamers) {
		this.gamersScore = findScoreBy(gamers);
		this.playersToProfit = findPlayersResult();
		this.dealerResult = findDealerResult();
	}

	private Map<Gamer, Score> findScoreBy(Gamers gamers) {
		return gamers.getGamers()
				.stream()
				.collect(Collectors.toMap(Function.identity(), Gamer::getScore));
	}

	private Map<Player, Profit> findPlayersResult() {
		Map<Player, Profit> playerToResult = new HashMap<>();
		Dealer dealer = findDealer();

		for (Player player : findPlayers()) {
			ResultType result = ResultType.of(player, dealer);
			playerToResult.put(player, new Profit(result.calculateProfit(player.getMoney())));
		}
		return playerToResult;
	}

	private Profit findDealerResult() {
		Profit profit = Profit.ZERO;
		for (Profit each : playersToProfit.values()) {
			profit = profit.plus(each.negative());
		}
		return profit;
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

	public Map<Player, Profit> getPlayersToProfit() {
		return playersToProfit;
	}

	public Profit getDealerResult() {
		return dealerResult;
	}
}
