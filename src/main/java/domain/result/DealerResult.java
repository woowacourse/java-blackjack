package domain.result;

import java.util.List;

public class DealerResult implements Result {

	private static final String DEALER_NAME = "딜러";
	private static final int DEALER_PLAYER_PROFIT_INVERSE_MULTIPLIER = -1;

	private final int profit;

	private DealerResult(final int profit) {
		this.profit = profit;
	}

	public static DealerResult from(final List<PlayerResult> playerResults) {
		int profit = DEALER_PLAYER_PROFIT_INVERSE_MULTIPLIER * playerResults.stream()
			.mapToInt(PlayerResult::getProfit)
			.sum();
		return new DealerResult(profit);
	}

	@Override
	public String getName() {
		return DEALER_NAME;
	}

	@Override
	public int getProfit() {
		return profit;
	}
}
