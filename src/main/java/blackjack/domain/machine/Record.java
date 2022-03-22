package blackjack.domain.machine;

import java.util.Arrays;
import java.util.Objects;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

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
        if (player.isBust() || dealer.isBust()) {
            return getRecordForBust(player, dealer);
        }

        return getOrdinaryRecord(player, dealer);
    }

    private static Record getOrdinaryRecord(Player player, Dealer dealer) {
        return Objects.requireNonNull(Arrays.stream(Record.values())
                .filter(record
                        -> record.recordNumber == player.isWin(dealer))
                .findFirst()
                .orElse(null));
    }

    private static Record getRecordForBust(Player player, Dealer dealer) {
        if (player.isBust()) {
            return DEFEAT;
        }

        if (dealer.isBust()) {
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