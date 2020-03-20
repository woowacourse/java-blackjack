package domain.result;

import java.util.List;
import java.util.Objects;

public class DealerResult {
	private static final int PROFIT_FORMULA = -1;

	private final int profit;

	public DealerResult(List<UserResult> playerResults) {
		Objects.requireNonNull(playerResults);
		this.profit = playerResults.stream()
			.map(UserResult::getProfit)
			.reduce(Integer::sum)
			.orElseThrow(IllegalArgumentException::new) * PROFIT_FORMULA;
	}

	public int getProfit() {
		return profit;
	}
}
