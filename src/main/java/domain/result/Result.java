package domain.result;

import java.util.HashMap;
import java.util.Map;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

public class Result {

	private final Map<Player, WinOrLose> playerResults;

	private Result(Map<Player, WinOrLose> playerResults) {
		this.playerResults = playerResults;
	}

	public static Result of(Dealer dealer, Players players) {
		Map<Player, WinOrLose> result = new HashMap<>();
		players.forEach(player -> result.putIfAbsent(player, WinOrLose.judgePlayerWinOrLose(dealer, player)));
		return new Result(result);
	}

	public int getDealerMoney() {
		return -1 * playerResults.entrySet().stream()
			.mapToDouble(entry -> entry.getKey().getBettingMoney() * entry.getValue().getEarningRate())
			.mapToInt(money -> (int)money)
			.sum();
	}

	public int getPlayerMoney(Player player) {
		return (int)(player.getBettingMoney() * playerResults.get(player).getEarningRate());
	}

}
