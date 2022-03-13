package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayerRecord {
	public static Map<String, String> of(Dealer dealer, List<Player> players) {
		Map<String, String> records = new LinkedHashMap<>();
		players.forEach(player
			-> records.put(player.getName(), getRecord(player, dealer)));

		return records;
	}

	private static String getRecord(Player player, Dealer dealer) {
		if(Record.isBurst(player, dealer)) {
			return Record.getRecordForBurst(player, dealer)
				.getPlayerRecord();
		}

		return getRecord(player, dealer);
	}
}
