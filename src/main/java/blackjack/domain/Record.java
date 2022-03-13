package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

	public static ResultDto of (Dealer dealer, List<Player> players) {
		Map<String, String> playerRecords = PlayerRecord.of(dealer,players);
		Map<String, Integer> dealerRecords = DealerRecord.of(dealer, players);

		PlayerResultDto playerResultDto = new PlayerResultDto(playerRecords);
		DealerResultDto dealerResultDto = new DealerResultDto(dealer.getName(), dealerRecords);

		return new ResultDto(dealerResultDto, playerResultDto);
	}

	public static int compare(Person person1, Person person2) {
		return Integer.compare(person1.score(), person2.score());
	}

	public static Record getRecord(Player player, Dealer dealer) {
		return Objects.requireNonNull(Arrays.stream(Record.values())
			.filter(record
				-> record.getRecordNumber() == compare(player, dealer))
			.findFirst()
			.orElse(null));
	}

	public static boolean isBurst(Player player, Dealer dealer) {
		return player.isBurst() || dealer.isBurst();
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

	public String getPlayerRecord() {
		return playerRecord;
	}

	public int getRecordNumber() {
		return recordNumber;
	}

	public String getDealerRecord() {
		return dealerRecord;
	}
}
