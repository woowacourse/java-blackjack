package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Profits {
	private Map<Name, Profit> profits;

	private Profits(Map<Name, Profit> profits) {
		this.profits = Collections.unmodifiableMap(profits);
	}

	public static Profits calculate(Dealer dealer, Players players, BettingMoneys bettingMoneys) {
		Map<Name, Profit> profits = new LinkedHashMap<>();
		profits.put(new Name(dealer.getName()), calculateByDealer(dealer, players, bettingMoneys));
		players.forEach(player -> profits.put(new Name(player.getName()), Profit.of(player, dealer, bettingMoneys.get(player))));
		return new Profits(profits);
	}

	private static Profit calculateByDealer(Dealer dealer, Players players, BettingMoneys bettingMoneys) {
		List<Profit> profits = new ArrayList<>();
		players.forEach(player -> profits.add(Profit.of(player, dealer, bettingMoneys.get(player))));
		return profits.stream()
				.reduce(Profit.ZERO, Profit::minus);
	}

	public Map<Name, Profit> getValue() {
		return profits;
	}
}
