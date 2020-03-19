package blackjack.domain.betting;

import blackjack.domain.user.Playable;
import blackjack.domain.user.Result;
import blackjack.domain.user.Results;

import java.util.LinkedHashMap;
import java.util.Map;

public class FinalMoniesFactory implements MoniesFactory {
	private final Map<Playable, Money> finalMonies;

	private FinalMoniesFactory(Map<Playable, Money> finalMonies) {
		this.finalMonies = finalMonies;
	}

	public static FinalMoniesFactory of(Monies monies, Results results) {
		Map<Playable, Money> finalMonies = new LinkedHashMap<>();
		for (Playable player : monies.ketSet()) {
			Money previous = monies.getMoney(player);
			Result result = results.getResult(player);
			finalMonies.put(
					player,
					previous.computeResultingAmount(result));
		}

		return new FinalMoniesFactory(finalMonies);
	}

	@Override
	public Monies create() {
		return new Monies(finalMonies);
	}
}
