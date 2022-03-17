package blackjack.domain.process;

import blackjack.domain.betting.BettingToken;
import blackjack.domain.betting.BettingTokens;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

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
		result.calculateProfitByCondition(((player) -> player.isWin(dealer) && player.isBlackJack()),
			BettingToken::getBlackJackWinningMoney);
		result.calculateProfitByCondition(((player) -> player.isWin(dealer) && !player.isBlackJack()),
			BettingToken::getNotBlackJackWinningMoney);
		result.calculateProfitByCondition(((player) -> player.isLose(dealer)), BettingToken::getLoseMoney);
		return result;
	}

	private void calculateProfitByCondition(Predicate<Gamer> condition, Consumer<BettingToken> action) {
		this.gameResult.entrySet().stream()
			.filter(entry -> condition.test(entry.getKey()))
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
