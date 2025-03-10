package blackjack.domain.round;

import java.util.List;
import java.util.function.BiFunction;

import blackjack.domain.gamer.Gamer;

public enum RoundResult {
    WIN("승"),
    LOSE("패"),
    TIE("무"),
    ;

    private final String displayName;

    RoundResult(String displayName) {
        this.displayName = displayName;
    }

    public static RoundResult judgeResult(Gamer gamer, Gamer otherGamer) {
        return getResultOf(
            List.of(
                RoundResult::getBustResult,
                RoundResult::getSumResult,
                RoundResult::getBlackjackResult),
            gamer, otherGamer);
    }

    private static RoundResult getResultOf(List<BiFunction<Gamer, Gamer, RoundResult>> getResultFunctions, Gamer gamer,
        Gamer otherGamer) {
        for (var function : getResultFunctions) {
            RoundResult roundResult = function.apply(gamer, otherGamer);
            if (roundResult != null) {
                return roundResult;
            }
        }
        return TIE;
    }

    private static RoundResult getBustResult(Gamer gamer, Gamer otherGamer) {
        if (gamer.isBust() && otherGamer.isBust()) {
            return TIE;
        }
        if (gamer.isBust()) {
            return LOSE;
        }
        if (otherGamer.isBust()) {
            return WIN;
        }
        return null;
    }

    private static RoundResult getSumResult(Gamer gamer, Gamer otherGamer) {
        int gamerSumOfCards = gamer.getSumOfCards();
        int otherGamerSumOfCards = otherGamer.getSumOfCards();

        if (gamerSumOfCards > otherGamerSumOfCards) {
            return WIN;
        }
        if (gamerSumOfCards < otherGamerSumOfCards) {
            return LOSE;
        }
        return null;
    }

    private static RoundResult getBlackjackResult(Gamer gamer, Gamer otherGamer) {
        if (gamer.isBlackjack() && otherGamer.isBlackjack()) {
            return TIE;
        }
        if (gamer.isBlackjack()) {
            return WIN;
        }
        if (otherGamer.isBlackjack()) {
            return LOSE;
        }
        return null;
    }

    public String getDisplayName() {
        return displayName;
    }
}
