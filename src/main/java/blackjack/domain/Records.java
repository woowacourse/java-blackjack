package blackjack.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.dto.DealerResultDto;
import blackjack.domain.dto.PlayerResultDto;
import blackjack.domain.dto.ResultDto;

public class Records {

	public static ResultDto of(Dealer dealer, List<Player> players) {
		Map<String, String> playerRecords = getPlayerRecords(dealer, players);
		Map<String, Integer> dealerRecords = getDealerRecords(dealer, players);

		PlayerResultDto playerResultDto = new PlayerResultDto(playerRecords);
		DealerResultDto dealerResultDto = new DealerResultDto(dealer.getName(), dealerRecords);

		return new ResultDto(dealerResultDto, playerResultDto);
	}

	private static Map<String, String> getPlayerRecords(Dealer dealer, List<Player> players) {
		Map<String, String> records = new LinkedHashMap<>();
		players.forEach(player
			-> records.put(player.getName(), Record.getRecord(player, dealer).getPlayerRecord()));

		return records;
	}

	private static Map<String, Integer> getDealerRecords(Dealer dealer, List<Player> players) {
		Map<String, Integer> records = Arrays.stream(Record.values())
			.collect(Collectors.toMap(Record::getPlayerRecord, record -> 0));

		for (Player player : players) {
			String record = Record.getRecord(player, dealer).getDealerRecord();
			records.put(record, records.getOrDefault(record, 0) + 1);
		}

		return records;
	}
}
