package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Results {
	private final Map<Name, Record> results;

	private Results(Map<Name, Record> results) {
		this.results = Collections.unmodifiableMap(results);
	}

	public Map<Name, Record> getValue() {
		return results;
	}

	public static Results calculate(Dealer dealer, Players players) {
		Map<Name, Record> results = new LinkedHashMap<>();
		results.put(new Name(dealer.getName()), calculateByDealer(dealer, players));
		players.forEach(player -> results.put(new Name(player.getName()), calculateByPlayer(player, dealer)));
		return new Results(results);
	}

	private static Record calculateByDealer(Dealer dealer, Players players) {
		List<Boolean> result = new ArrayList<>();
		players.forEach(player -> result.add(dealer.isWin(player)));
		int winCount = (int)result.stream().filter(Boolean::booleanValue).count();
		int loseCount = result.size() - winCount;
		return new Record(winCount, loseCount);
	}

	private static Record calculateByPlayer(Player player, Dealer dealer) {
		if (player.isWin(dealer)) {
			return new Record(1, 0);
		}
		return new Record(0, 1);
	}
}
