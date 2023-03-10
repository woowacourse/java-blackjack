package domain.result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DealerResult {

	private static final String DEALER_NAME = "딜러";

	private final Map<ResultState, Integer> result;

	private DealerResult(final Map<ResultState, Integer> result) {
		this.result = result;
	}

	public static DealerResult from(final List<PlayerResult> playerPlayerResults) {
		final Map<ResultState, Integer> dealerResult = new LinkedHashMap<>();
		int playerWins = countResultState(playerPlayerResults, ResultState.WIN);
		int playerDraws = countResultState(playerPlayerResults, ResultState.DRAW);
		int playerLosses = countResultState(playerPlayerResults, ResultState.LOSS);

		dealerResult.put(ResultState.WIN, playerLosses);
		dealerResult.put(ResultState.DRAW, playerDraws);
		dealerResult.put(ResultState.LOSS, playerWins);
		return new DealerResult(dealerResult);
	}

	private static int countResultState(final List<PlayerResult> playerPlayerResults, final ResultState state) {
		return (int) playerPlayerResults.stream()
			.filter(playerResult -> playerResult.getResultState() == state)
			.count();
	}

	public String getName() {
		return DEALER_NAME;
	}

	public Map<ResultState, Integer> getResult() {
		return Collections.unmodifiableMap(result);
	}
}
