package domains.result;

import java.util.HashMap;
import java.util.Map;

import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;

public class GameResult {
	private static final String BLANK = " ";
	private Map<Player, WinOrLose> gameResult;

	public GameResult() {
		this.gameResult = new HashMap<>();
	}

	public void create(Players players, Dealer dealer) {
		for (Player player : players) {
			if (checkBurstPlayer(player)) {
				continue;
			}
			gameResult.put(player, WinOrLose.checkWinOrLose(player, dealer));
		}
	}

	private boolean checkBurstPlayer(Player player) {
		if (player.isBurst()) {
			gameResult.put(player, WinOrLose.LOSE);
			return true;
		}
		return false;
	}

	public WinOrLose getWinOrLose(Player player) {
		return gameResult.get(player);
	}

	public String calculateDealerResult() {
		int winCount = 0;
		int loseCount = 0;
		int drawCount = 0;

		for (WinOrLose result : gameResult.values()) {
			if (result.equals(WinOrLose.WIN)) {
				loseCount++;
				continue;
			}
			if (result.equals(WinOrLose.DRAW)) {
				drawCount++;
				continue;
			}
			if (result.equals(WinOrLose.LOSE)) {
				winCount++;
			}
		}
		return winCount + WinOrLose.WIN.getWinOrLose() + BLANK
			+ drawCount + WinOrLose.DRAW.getWinOrLose()+ BLANK
			+ loseCount + WinOrLose.LOSE.getWinOrLose();
	}

	public Map<Player, WinOrLose> getGameResult() {
		return gameResult;
	}
}
