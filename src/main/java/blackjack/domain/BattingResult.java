package blackjack.domain;

import blackjack.domain.participant.BattingMoney;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;

public enum BattingResult {

    WIN((score, otherScore) -> score > otherScore, battingMoney -> battingMoney),
    WIN_BY_BLACKJACK((score, otherScore) -> score == 21 && score > otherScore, battingMoney -> battingMoney.times(1.5)),
    TIE((score, otherScore) -> score == otherScore, battingMoney -> battingMoney.times(0)),
    LOSE((score, otherScore) -> score < otherScore, battingMoney -> battingMoney.times(-1))
    ;

    private final BiPredicate<Integer, Integer> condition;
    private final UnaryOperator<BattingMoney> operator;

    BattingResult(BiPredicate<Integer, Integer> condition,
                  UnaryOperator<BattingMoney> operator) {
        this.condition = condition;
        this.operator = operator;
    }

    public static BattingResult of(int score, int otherScore) {
        return Arrays.stream(values())
                .filter(battingResult -> battingResult.condition.test(score, otherScore))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("결과가 정상적으로 나오지 않았습니다."));
    }

    public int getResult(BattingMoney battingMoney) {
        return operator.apply(battingMoney).getAmount();
    }
}
