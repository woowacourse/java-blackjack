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
	private final Map<Gamer, Profit> gamersProfit;

	public GameResult(Gamers gamers) {
		this.gamersScore = findScoreBy(gamers);
		this.gamersProfit = calculateGamersProfit();
	}

	private Map<Gamer, Score> findScoreBy(Gamers gamers) {
		return gamers.getGamers()
				.stream()
				.collect(Collectors.toMap(Function.identity(), Gamer::getScore));
	}

	private Map<Gamer, Profit> calculateGamersProfit() {
		Dealer dealer = findDealer();

		Map<Gamer, Profit> gamersProfit = new HashMap<>(calculatePlayersProfit(dealer));

		List<Profit> playersProfit = new ArrayList<>(gamersProfit.values());
		gamersProfit.put(dealer, calculateDealerProfit(playersProfit));

		return gamersProfit;
	}

	private Map<Gamer, Profit> calculatePlayersProfit(Dealer dealer) {
		Map<Gamer, Profit> playersResult = new HashMap<>();

		for (Player player : findPlayers()) {
			ResultType result = ResultType.of(player, dealer);
			playersResult.put(player, new Profit(result.calculateProfit(player.getMoney())));
		}
		return playersResult;
	}

	private Profit calculateDealerProfit(List<Profit> playersProfit) {
		Profit profit = Profit.ZERO;
		for (Profit eachProfit : playersProfit) {
			profit = profit.plus(eachProfit.negative());
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

	public Map<Gamer, Profit> getGamersProfit() {
		return gamersProfit;
	}
}
