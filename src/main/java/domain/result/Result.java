package domain.result;

import java.util.LinkedHashMap;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Players;

public class Result {

	private final LinkedHashMap<Participant, EarningRate> playerResults;

	private Result(LinkedHashMap<Participant, EarningRate> playerResults) {
		this.playerResults = playerResults;
	}

	public static Result of(Dealer dealer, Players players) {
		LinkedHashMap<Participant, EarningRate> result = players.getResult(dealer);
		return new Result(result);
	}

	public int getDealerMoney() {
		return -1 * playerResults.entrySet().stream()
			.mapToDouble(entry -> entry.getKey().showBetting() * entry.getValue().getEarningRate())
			.mapToInt(money -> (int)money)
			.sum();
	}

	public LinkedHashMap<Participant, EarningRate> getPlayerResults() {
		return playerResults;
	}
}
