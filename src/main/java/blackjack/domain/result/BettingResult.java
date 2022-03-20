package blackjack.domain.result;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingResult {

	private final Map<String, Integer> playerEarnings;
	private final int dealerEarning;

	public BettingResult(Map<String, Integer> playerEarnings) {
		this.playerEarnings = new LinkedHashMap<>(playerEarnings);
		this.dealerEarning = this.playerEarnings.values().stream()
			.reduce(0, Integer::sum) * -1;
	}

	public int getDealerEarning() {
		return dealerEarning;
	}

	public Map<String, Integer> getPlayerEarnings() {
		return new LinkedHashMap<>(playerEarnings);
	}
}
