package blackjack.domain.round;

import blackjack.domain.gamer.Gamer;

import java.util.List;
import java.util.function.BiFunction;

public enum RoundResult {
    WIN(1),
    LOSE(-1),
    TIE(0),
    BLACKJACK_WIN(1.5),
    BUST(-1);

    private final double profitMultiplier;

    RoundResult(double profitMultiplier) {
        this.profitMultiplier = profitMultiplier;
    }

    public static RoundResult judgeResult(Gamer gamer, Gamer otherGamer) {
        return getResultOf(
                List.of(
                        RoundResult::getBustResult,
                        RoundResult::getBlackjackResult,
                        RoundResult::getSumResult),
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
        if (gamer.isBust()) {
            return BUST;
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
            return BLACKJACK_WIN;
        }
        if (otherGamer.isBlackjack()) {
            return LOSE;
        }
        return null;
    }

    public double getProfitMultiplier() {
        return profitMultiplier;
    }
}
