package blackjack.domain;

import blackjack.domain.participant.Participant;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public enum GameOutcome {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String printValue;

    GameOutcome(final String printValue) {
        this.printValue = printValue;
    }

    public static GameOutcome calculateOutcome(final Participant player, final Participant dealer) {
        validateDealer(dealer);
        return calculateOutcomeCase(player, dealer);
    }

    private static void validateDealer(final Participant dealer) {
        if (!dealer.isDealer()) {
            throw new IllegalStateException("딜러가 아닌 사람과 비교할 수 없습니다.");
        }
    }

    private static GameOutcome calculateOutcomeCase(final Participant player, final Participant dealer) {
        if (player.isBust()) {
            return LOSE;
        }
        if (player.isBlackJack()) {
            return playerInBlackJack(dealer);
        }
        if (dealer.isBust()) {
            return WIN;
        }
        if (dealer.isBlackJack()) {
            return LOSE;
        }
        return calculateOutcome(player.calculateResultScore(), dealer.calculateResultScore());
    }

    private static GameOutcome playerInBlackJack(final Participant dealer) {
        if (dealer.isBlackJack()) {
            return DRAW;
        }
        return WIN;
    }

    private static GameOutcome calculateOutcome(final int score, final int compareScore) {
        if (score > compareScore) {
            return WIN;
        } else if (score < compareScore) {
            return LOSE;
        }
        return DRAW;
    }

    public static Map<GameOutcome, Integer> createInitMap() {
        Map<GameOutcome, Integer> results = new EnumMap<>(GameOutcome.class);
        Arrays.stream(values())
                .forEach(key -> results.merge(key, 0, (x, y) -> y));
        return results;
    }

    public GameOutcome reverse() {
        if (this == WIN) {
            return LOSE;
        } else if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getPrintValue() {
        return printValue;
    }
}
