package domain.result;

import java.util.LinkedHashMap;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;

public class Result {

	private final LinkedHashMap<Participant, WinOrLose> playerResults;

	private Result(LinkedHashMap<Participant, WinOrLose> playerResults) {
		this.playerResults = playerResults;
	}

	public static Result of(Dealer dealer, Players players) {
		LinkedHashMap<Participant, WinOrLose> result = players.getResult(dealer);
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
