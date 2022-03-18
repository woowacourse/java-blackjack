package domain.result;

import java.util.LinkedHashMap;

import domain.participant.Participant;

public class Result {

	private final LinkedHashMap<Participant, EarningRate> playerResults;

	public Result(LinkedHashMap<Participant, EarningRate> playerResults) {
		this.playerResults = playerResults;
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
