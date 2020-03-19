package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Profits {
	private final Map<Name, Profit> profits;

	private Profits(Map<Name, Profit> profits) {
		this.profits = Collections.unmodifiableMap(profits);
	}

	public static Profits calculate(Dealer dealer, Players players, BettingMoneys bettingMoneys) {
		Map<Name, Profit> playersProfit = new LinkedHashMap<>();
		players.forEach(player -> playersProfit.put(new Name(player.getName()),
				Profit.of(player, dealer, bettingMoneys.get(player))));
		Map<Name, Profit> profits = new LinkedHashMap<>();
		Profit dealerProfit = playersProfit.values()
				.stream()
				.reduce(Profit.ZERO, Profit::minus);
		profits.put(new Name(dealer.getName()), dealerProfit);
		profits.putAll(playersProfit);
		return new Profits(profits);
	}

	public Map<Name, Profit> getValue() {
		return profits;
	}
}
