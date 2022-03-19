package blackjack.domain.process;

import blackjack.domain.betting.BettingToken;
import blackjack.domain.betting.Profit;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingResult {
	private static final int NEGATE = -1;

	private final Map<Player, BettingToken> playersBettingTokenCache;
	private final Map<Gamer, Profit> GamersBettingResult;

	private BettingResult(Players players) {
		playersBettingTokenCache = new LinkedHashMap<>();
		GamersBettingResult = new LinkedHashMap<>();
		for (Player player : players.getPlayers()) {
			playersBettingTokenCache.put(player, player.getBettingToken());
		}
	}

	public static BettingResult of(Players players, Dealer dealer) {
		BettingResult bettingResult = new BettingResult(players);
		Map<Player, Match> matchResultCache = MatchResult.of(players, dealer).getPlayersMatchCache();
		int dealerProfit = bettingResult.calculateSumOfPlayersProfit(matchResultCache);
		bettingResult.GamersBettingResult.put(dealer, Profit.of(Match.NONE, NEGATE * dealerProfit));
		players.getPlayers().forEach(player -> bettingResult.GamersBettingResult.put(player,
			Profit.of(matchResultCache.get(player), bettingResult.playersBettingTokenCache.get(player).getMoney())));
		return bettingResult;
	}

	private int calculateSumOfPlayersProfit(Map<Player, Match> playersMatchResult) {
		return playersMatchResult.keySet().stream()
			.mapToInt(player -> Profit.of(playersMatchResult.get(player),
					this.playersBettingTokenCache.get(player).getMoney())
				.getPrizeValue())
			.sum();
	}

	public Map<Gamer, Profit> getGamersBettingResult() {
		return GamersBettingResult;
	}
}
