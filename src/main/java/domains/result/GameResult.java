package domains.result;

import java.util.Map;

import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;

public class GameResult {
	private Map<Player, ResultType> playerResult;

	public GameResult(Players players, Dealer dealer) {
		this.playerResult = GameResultFactory.create(players, dealer);
	}

	public ResultType getWinOrLose(Player player) {
		return playerResult.get(player);
	}

	Map<Player, ResultType> getPlayerResult() {
		return playerResult;
	}
}
