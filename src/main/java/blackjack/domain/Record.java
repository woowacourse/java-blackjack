package blackjack.domain;

import static blackjack.domain.RecordFactory.*;

import java.util.Arrays;

public enum Record {

    WIN("승", "패"),
    PUSH("무", "무"),
    LOSS("패", "승");

    private final String name;
    private final String opposite;

    Record(String name, String opposite) {
        this.name = name;
        this.opposite = opposite;
    }

    public static Record of(boolean isDealerBust, int dealerScore, int score) {
        if (isDealerBust) {
            return getRecordWhenDealerBust(score);
        }

        if (score > BUST_SCORE || score < dealerScore) {
            return LOSS;
        }

        if (dealerScore == score) {
            return PUSH;
        }

        return WIN;
    }

    private static Record getRecordWhenDealerBust(int score) {
        if (score > BUST_SCORE) {
            return LOSS;
        }

        return WIN;
    }

    public String getName() {
        return name;
    }

    Record getOpposite() {
        return Arrays.stream(Record.values())
            .filter(record -> record.name.equals(opposite))
            .findFirst()
            .orElseThrow();
    }
}
