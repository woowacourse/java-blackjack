package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class Result {
	private static final int NEGATE = -1;
	private final Map<Player, BettingToken> gameResult;

	public Result(Players players, BettingTokens bettingTokens) {
		gameResult = new LinkedHashMap<>();
		List<Player> nowPlayers = players.getPlayers();
		List<BettingToken> nowBettingTokens = bettingTokens.getBettingMonies();
		for (int i = 0; i < nowPlayers.size(); i++) {
			gameResult.put(nowPlayers.get(i), nowBettingTokens.get(i));
		}
	}

	public static Result of(Players players, Dealer dealer, BettingTokens bettingTokens) {
		Result result = new Result(players, bettingTokens);
		result.calculateProfitByCondition(dealer, Gamer::isWinByBlackJack, BettingToken::getBlackJackWinningMoney);
		result.calculateProfitByCondition(dealer, Gamer::isWinByNotBlackJack,
			BettingToken::getNotBlackJackWinningMoney);
		result.calculateProfitByCondition(dealer, Gamer::isLose, BettingToken::getLoseMoney);
		return result;
	}

	private void calculateProfitByCondition(Dealer dealer, BiPredicate<Player, Dealer> condition,
											Consumer<BettingToken> action) {
		this.gameResult.entrySet().stream()
			.filter(entry -> condition.test(entry.getKey(), dealer))
			.forEach(entry -> action.accept(entry.getValue()));
	}

	public int getDealerMoney() {
		return NEGATE * gameResult.values().stream()
			.mapToInt(BettingToken::getProfit)
			.sum();
	}

	public Map<String, Integer> getPlayersMoney() {
		Map<String, Integer> playersMoney = new LinkedHashMap<>();
		for (Map.Entry<Player, BettingToken> entry : this.gameResult.entrySet()) {
			playersMoney.put(entry.getKey().getName(), entry.getValue().getProfit());
		}
		return playersMoney;
	}
}
