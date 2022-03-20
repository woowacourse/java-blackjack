package blackjack.domain.game;

import java.util.Arrays;

import org.assertj.core.util.TriFunction;

public enum PlayRecord {

    LOSS((isPlayerLoss, isSameScore, isBlackjack) -> isPlayerLoss),
    PUSH((isPlayerLoss, isSameScore, isBlackjack) -> !isPlayerLoss && isSameScore),
    BLACKJACK((isPlayerLoss, isSameScore, isBlackjack) -> isBlackjack),
    WIN((isPlayerLoss, isSameScore, isBlackjack) -> true);

    private final TriFunction<Boolean, Boolean, Boolean, Boolean> isMatch;

    PlayRecord(TriFunction<Boolean, Boolean, Boolean, Boolean> isMatch) {
        this.isMatch = isMatch;
    }

    static PlayRecord of(boolean isPlayerLoss, boolean isSameScore, boolean isBlackjack) {
        return Arrays.stream(values())
            .filter(playRecord -> playRecord.isMatch.apply(isPlayerLoss, isSameScore, isBlackjack))
            .findFirst()
            .orElse(WIN);
    }
}
