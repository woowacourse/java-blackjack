package blackjack.domain;

import static blackjack.domain.PlayStatus.*;

import java.util.Arrays;

public enum PlayRecord {

    WIN("승", "패"),
    PUSH("무", "무"),
    LOSS("패", "승");

    private final String name;
    private final String opposite;

    PlayRecord(String name, String opposite) {
        this.name = name;
        this.opposite = opposite;
    }

    public static PlayRecord of(int dealerScore, int score) {
        if (isPlayerLoss(dealerScore, score)) {
            return LOSS;
        }

        if (dealerScore == score) {
            return PUSH;
        }

        return WIN;
    }

    private static boolean isPlayerLoss(int dealerScore, int score) {
        return isBust(score) || (!isBust(dealerScore) && score < dealerScore);
    }

    public String getName() {
        return name;
    }

    PlayRecord getOpposite() {
        return Arrays.stream(PlayRecord.values())
            .filter(record -> record.name.equals(opposite))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("옳지 않은 결과 입력입니다."));
    }
}
