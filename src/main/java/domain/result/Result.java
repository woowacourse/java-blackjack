package domain.result;

import java.util.Map;

import domain.participant.ParticipantInfo;

public class Result {

	private final Map<ParticipantInfo, EarningRate> playerResults;

	public Result(Map<ParticipantInfo, EarningRate> playerResults) {
		this.playerResults = Map.copyOf(playerResults);
	}

	public int getDealerMoney() {
		return -1 * playerResults.entrySet().stream()
			.mapToDouble(entry -> entry.getKey().getBetting().getBettingMoney() * entry.getValue().getEarningRate())
			.mapToInt(money -> (int)money)
			.sum();
	}

	public Map<ParticipantInfo, EarningRate> getPlayerResults() {
		return playerResults;
	}
}
