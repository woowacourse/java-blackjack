package blackjack.domain;

import blackjack.domain.participant.BettingMoney;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;

public enum BettingResult {

    WIN((score, otherScore) -> score > otherScore, bettingMoney -> bettingMoney),
    WIN_BY_BLACKJACK((score, otherScore) -> score == 21 && score > otherScore, bettingMoney -> bettingMoney.times(1.5)),
    TIE((score, otherScore) -> score == otherScore, bettingMoney -> bettingMoney.times(0)),
    LOSE((score, otherScore) -> score < otherScore, bettingMoney -> bettingMoney.times(-1))
    ;

    private final BiPredicate<Integer, Integer> condition;
    private final UnaryOperator<BettingMoney> operator;

    BettingResult(BiPredicate<Integer, Integer> condition,
                  UnaryOperator<BettingMoney> operator) {
        this.condition = condition;
        this.operator = operator;
    }

    public static BettingResult of(int score, int otherScore) {
        return Arrays.stream(values())
                .filter(bettingResult -> bettingResult.condition.test(score, otherScore))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("결과가 정상적으로 나오지 않았습니다."));
    }

    public int getResult(BettingMoney bettingMoney) {
        return operator.apply(bettingMoney).getAmount();
    }
}
