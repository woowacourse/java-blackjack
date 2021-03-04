package blakcjack.domain;

import java.util.Map;

public class OutcomeStatistics {
	private final Map<Outcome, Integer> dealerOutcome;
	private final Map<String, Outcome> playersOutcome;

	public OutcomeStatistics(final Map<Outcome, Integer> dealerOutcome, final Map<String, Outcome> playersOutcome) {
		this.dealerOutcome = dealerOutcome;
		this.playersOutcome = playersOutcome;
	}

	public int getDealerWinCount() {
		return dealerOutcome.get(Outcome.WIN);
	}

	public int getDealerDrawCount() {
		return dealerOutcome.get(Outcome.DRAW);
	}

	public int getDealerLoseCount() {
		return dealerOutcome.get(Outcome.LOSE);
	}

	public Map<String, Outcome> getPlayersOutcome() {
		return playersOutcome;
	}
}
