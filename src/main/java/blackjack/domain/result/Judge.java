package blackjack.domain.result;

import blackjack.domain.card.Cards;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public enum Judge {
    PLAYER_BLACK_JACK(Cards::isBlackJack, (playerCards, dealerCards) -> {
        if (dealerCards.isBlackJack()) {
            return ResultType.DRAW;
        }
        return ResultType.BLACK_JACK;
    }),
    PLAYER_BUST(Cards::isBust, (playerCards, dealerCards) -> {
        return ResultType.LOSE;
    }),
    PLAYER_NORMAL(Cards::isNormal, (playerCards, dealerCards) -> {
        if (dealerCards.isBlackJack()) {
            return ResultType.LOSE;
        }

        int playerDiff = playerCards.getDiffWithBlackJack();
        int dealerDiff = dealerCards.getDiffWithBlackJack();
        if (dealerDiff > playerDiff) {
            return ResultType.WIN;
        }
        if (playerDiff == dealerDiff) {
            return ResultType.DRAW;
        }
        return ResultType.LOSE;
    });

    Predicate<Cards> judgeState;
    BiFunction<Cards, Cards, ResultType>   judgeResultType;

    Judge(Predicate<Cards> judgeState, BiFunction<Cards, Cards, ResultType> judgeResultTYpe) {
        this.judgeState = judgeState;
        this.judgeResultType = judgeResultTYpe;
    }
}
