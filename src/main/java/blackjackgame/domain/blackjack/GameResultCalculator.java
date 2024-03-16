package blackjackgame.domain.blackjack;

import static blackjackgame.domain.blackjack.CardResult.BLACKJACK;
import static blackjackgame.domain.blackjack.CardResult.BUST;
import static blackjackgame.domain.blackjack.GameResult.BIG_WIN;
import static blackjackgame.domain.blackjack.GameResult.LOSE;
import static blackjackgame.domain.blackjack.GameResult.WIN;

import blackjackgame.domain.gamers.CardHolder;

public class GameResultCalculator {

    private GameResultCalculator() {
    }

    public static GameResult calculate(CardHolder baseCardHolder, CardHolder otherCardHolder) {
        CardResult baseCardResult = baseCardHolder.getCardResult();
        CardResult otherCardResult = otherCardHolder.getCardResult();

        if (baseCardResult == BLACKJACK) {
            return calculateWhenBaseBlackjack(otherCardResult);
        }
        if (baseCardResult == BUST) {
            return LOSE;
        }
        return calculateWhenBaseNormal(otherCardResult,
                baseCardHolder.getSummationCardPoint(), otherCardHolder.getSummationCardPoint());
    }

    private static GameResult calculateWhenBaseBlackjack(CardResult otherCardResult) {
        if (otherCardResult == BLACKJACK) {
            return WIN;
        }
        return BIG_WIN;
    }

    private static GameResult calculateWhenBaseNormal(CardResult otherCardResult, SummationCardPoint baseSummation,
                                                      SummationCardPoint otherSummation) {
        if (otherCardResult == BUST) {
            return WIN;
        }
        if (otherSummation.isBiggerThan(baseSummation)) {
            return LOSE;
        }
        return WIN;
    }
}
