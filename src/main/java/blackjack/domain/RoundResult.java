package blackjack.domain;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

import blackjack.domain.gamer.Gamer;

public enum RoundResult {
    WIN(2),
    LOSE(1.5),
    TIE(1),
    BLACKJACK(1.5),
    ;

    private final double bettingAmountMultiple;

    RoundResult(double bettingAmountMultiple) {
        this.bettingAmountMultiple = bettingAmountMultiple;
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
        return getResultFunctions.stream()
            .map(function -> function.apply(gamer, otherGamer))
            .filter(Objects::nonNull)
            .findFirst()
            .orElse(TIE);
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

    public double getBettingAmountMultiple() {
        return bettingAmountMultiple;
    }
}
