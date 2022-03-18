package domain.result;

import java.util.LinkedHashMap;

import domain.participant.info.ParticipantInfo;

public class Result {

	private final LinkedHashMap<ParticipantInfo, EarningRate> playerResults;

	public Result(LinkedHashMap<ParticipantInfo, EarningRate> playerResults) {
		this.playerResults = playerResults;
	}

	public int getDealerMoney() {
		return -1 * playerResults.entrySet().stream()
			.mapToDouble(entry -> entry.getKey().getBetting().getBettingMoney() * entry.getValue().getEarningRate())
			.mapToInt(money -> (int)money)
			.sum();
	}

	public LinkedHashMap<ParticipantInfo, EarningRate> getPlayerResults() {
		return playerResults;
	}
}
