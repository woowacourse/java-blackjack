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
		players.forEach(player -> results.put(new Name(player.getName()), Record.of(player, dealer)));
		return new Results(results);
	}

	private static Record calculateByDealer(Dealer dealer, Players players) {
		List<Record> records = new ArrayList<>();
		players.forEach(player -> records.add(Record.of(dealer, player)));
		return records.stream()
				.reduce(Record::add)
				.get();
	}
}
