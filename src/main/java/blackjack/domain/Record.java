package blackjack.domain;

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
        if (isPlayerLoss(isDealerBust, dealerScore, score)) {
            return LOSS;
        }

        if (dealerScore == score) {
            return PUSH;
        }

        return WIN;
    }

    private static boolean isPlayerLoss(boolean isDealerBust, int dealerScore, int score) {
        return PlayStatus.isBust(score) || (!isDealerBust && score < dealerScore);
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
