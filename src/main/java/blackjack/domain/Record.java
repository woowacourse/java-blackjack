package blackjack.domain;

import java.util.Arrays;
import java.util.Objects;

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

	public static Record getRecord(Player player, Dealer dealer) {
		if (isBurst(player, dealer)) {
			return getRecordForBurst(player, dealer);
		}

		return getOrdinaryRecord(player, dealer);
	}

	private static Record getOrdinaryRecord(Player player, Dealer dealer) {
		return Objects.requireNonNull(Arrays.stream(Record.values())
			.filter(record
				-> record.recordNumber == compare(player, dealer))
			.findFirst()
			.orElse(null));
	}

	private static int compare(Participant participant1, Participant participant2) {
		return Integer.compare(participant1.score(), participant2.score());
	}

	private static boolean isBurst(Player player, Dealer dealer) {
		return player.isBurst() || dealer.isBurst();
	}

	private static Record getRecordForBurst(Player player, Dealer dealer) {
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

	public String getDealerRecord() {
		return dealerRecord;
	}
}
