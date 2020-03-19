package blackjack.domain.betting;

import blackjack.domain.user.Name;
import blackjack.domain.user.Result;
import blackjack.domain.user.Results;

import java.util.LinkedHashMap;
import java.util.Map;

public final class FinalMoniesFactory implements MoniesFactory {
	private final Map<Name, Money> finalMonies;

	private FinalMoniesFactory(Map<Name, Money> finalMonies) {
		this.finalMonies = finalMonies;
	}

	public static FinalMoniesFactory of(Monies monies, Results results) {
		Map<Name, Money> finalMonies = new LinkedHashMap<>();
		for (Name name : monies.keySet()) {
			Money previous = monies.getMoney(name);
			Result result = results.getResult(name);
			finalMonies.put(name, previous.computeResultingAmount(result));
		}

		return new FinalMoniesFactory(finalMonies);
	}

	@Override
	public Monies create() {
		return new Monies(finalMonies);
	}
}
