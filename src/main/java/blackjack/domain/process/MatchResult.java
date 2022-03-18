package blackjack.domain.process;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class MatchResult {
	private final Map<Player, Match> playersMatchCache;

	private MatchResult(Players players) {
		this.playersMatchCache = new LinkedHashMap<>();
		for (Player player : players.getPlayers()) {
			this.playersMatchCache.put(player, Match.DRAW);
		}
	}

	public static MatchResult of(Players players, Dealer dealer) {
		MatchResult matchResult = new MatchResult(players);
		matchResult.generateResultByCondition(((player) -> player.isWin(dealer) && player.isBlackJack()),
			Match.BLACKJACK_WIN);
		matchResult.generateResultByCondition(((player) -> player.isWin(dealer) && !player.isBlackJack()),
			Match.NOT_BLACKJACK_WIN);
		matchResult.generateResultByCondition(((player) -> player.isLose(dealer)),
			Match.LOSE);
		return matchResult;
	}

	private void generateResultByCondition(Predicate<Gamer> condition, Match match) {
		this.playersMatchCache.entrySet().stream()
			.filter(entry -> condition.test(entry.getKey()))
			.forEach(entry -> this.playersMatchCache.put(entry.getKey(), match));
	}

	public Map<Player, Match> getPlayersMatchCache() {
		return playersMatchCache;
	}
}
