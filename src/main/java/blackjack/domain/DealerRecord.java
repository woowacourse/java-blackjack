package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DealerRecord {

	public static Map<String, Integer> of(Dealer dealer, List<Player> players) {
		Map<String, Integer> records = Arrays.stream(Record.values())
			.collect(Collectors.toMap(Record::getPlayerRecord, record -> 0));

		for(Player player : players) {
			String record = getRecord(dealer, player);
			records.put(record, records.getOrDefault(record, 0) + 1);
		}

		return records;
	}

	private static String getRecord(Dealer dealer, Player player) {
		if(Record.isBurst(player, dealer)) {
			return Record.getRecordForBurst(player, dealer)
				.getDealerRecord();
		}

		return Record.getRecord(player, dealer)
			.getDealerRecord();
	}
}
