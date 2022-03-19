package blackjack.domain.result;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingResult {

	private final int dealerEarning;
	private final Map<String, Integer> playerEarnings;

	public BettingResult(int dealerEarning, Map<String, Integer> playerEarnings) {
		this.dealerEarning = dealerEarning;
		this.playerEarnings = new LinkedHashMap<>(playerEarnings);
	}

	public int getDealerEarning() {
		return dealerEarning;
	}

	public Map<String, Integer> getPlayerEarnings() {
		return new LinkedHashMap<>(playerEarnings);
	}
}
