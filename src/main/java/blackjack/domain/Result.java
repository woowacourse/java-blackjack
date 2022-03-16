package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {
	private final Map<Player, BettingToken> playerBettingTokenCache;
	private final Map<String, Integer> playersMoneyResult;

	public Result(Players players, BettingTokens bettingTokens) {
		playerBettingTokenCache = new LinkedHashMap<>();
		playersMoneyResult = new LinkedHashMap<>();
		List<Player> nowPlayers = players.getPlayers();
		List<BettingToken> nowBettingTokens = bettingTokens.getBettingMonies();
		for (int i = 0; i < nowPlayers.size(); i++) {
			playerBettingTokenCache.put(nowPlayers.get(i), nowBettingTokens.get(i));
			playersMoneyResult.put(nowPlayers.get(i).getName(), nowBettingTokens.get(i).getMoney());
		}
	}

	public int getDealerMoney(Dealer dealer) {
		int dealerMoney = playerBettingTokenCache.values().stream()
			.mapToInt(BettingToken::getMoney)
			.sum();
		dealerMoney -= playerBettingTokenCache.keySet().stream()
			.filter(player -> player.isWinByBlackJack(dealer))
			.mapToInt(player -> playerBettingTokenCache.get(player).getBlackJackMoney()).sum()
			+ playerBettingTokenCache.keySet().stream()
			.filter(player -> (player.isWin(dealer) && !player.isWinByBlackJack(dealer)) || player.isDraw(dealer))
			.mapToInt(player -> playerBettingTokenCache.get(player).getMoney()).sum();
		return dealerMoney;
	}

	public Map<String, Integer> getPlayersMoney(Dealer dealer) {
		playerBettingTokenCache.keySet().stream()
			.filter(player -> player.isWinByBlackJack(dealer))
			.forEach(
				player -> playersMoneyResult.put(player.getName(),
					playerBettingTokenCache.get(player).getBlackJackMoney()));
		playerBettingTokenCache.keySet().stream()
			.filter(player -> player.isLose(dealer))
			.forEach(
				player -> playersMoneyResult.put(player.getName(), playerBettingTokenCache.get(player).getLoseMoney()));
		return playersMoneyResult;
	}
}
