package blackjack.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import blackjack.domain.dto.DealerResultDto;
import blackjack.domain.dto.PlayerResultDto;
import blackjack.domain.dto.ResultDto;

public enum Record {
	VICTORY("승", 1, "패"),
	DRAW("무", 0, "무"),
	DEFEAT("패", -1, "승");

	private final String playerRecord;
	private final int recordNumber;
	private final String dealerRecord;

	Record(String playerRecord, int recordNumber, String dealerRecord) {
		this.playerRecord = playerRecord;
		this.recordNumber = recordNumber;
		this.dealerRecord = dealerRecord;
	}

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
			-> records.put(player.getName(), getRecord(player, dealer).playerRecord));

		return records;
	}

	private static Map<String, Integer> getDealerRecords(Dealer dealer, List<Player> players) {
		Map<String, Integer> records = Arrays.stream(Record.values())
			.collect(Collectors.toMap(record -> record.playerRecord, record -> 0));

		for (Player player : players) {
			String record = getRecord(player, dealer).dealerRecord;
			records.put(record, records.getOrDefault(record, 0) + 1);
		}

		return records;
	}

	public static Record getRecord(Player player, Dealer dealer) {
		if (isBurst(player, dealer)) {
			return getRecordForBurst(player, dealer);
		}

		return getOrdinaryRecord(player, dealer);
	}

	public static Record getOrdinaryRecord(Player player, Dealer dealer) {
		return Objects.requireNonNull(Arrays.stream(Record.values())
			.filter(record
				-> record.recordNumber == compare(player, dealer))
			.findFirst()
			.orElse(null));
	}

	private static int compare(Person person1, Person person2) {
		return Integer.compare(person1.score(), person2.score());
	}

	public static Record getRecordForBurst(Player player, Dealer dealer) {
		if (dealer.isBurst() && player.isBurst()) {
			return DEFEAT;
		}

		if (dealer.isBurst()) {
			return VICTORY;
		}

		return DEFEAT;
	}

	private static boolean isBurst(Player player, Dealer dealer) {
		return player.isBurst() || dealer.isBurst();
	}
}
