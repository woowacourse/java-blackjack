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
		players.forEach(player -> results.put(new Name(player.getName()), calculateByUser(player, dealer)));
		return new Results(results);
	}

	private static Record calculateByDealer(Dealer dealer, Players players) {
		List<Record> records = new ArrayList<>();
		players.forEach(player -> records.add(calculateByUser(dealer, player)));
		return records.stream()
				.reduce(Record::add)
				.get();
	}

	private static Record calculateByUser(User source, User target) {
		if (source.isWin(target)) {
			return new Record(1, 0, 0);
		}
		if (source.isDraw(target)) {
			return new Record(0, 1, 0);
		}
		return new Record(0, 0, 1);
	}
}
