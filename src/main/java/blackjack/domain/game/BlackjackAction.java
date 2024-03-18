package blackjack.domain.game;

import blackjack.utils.constants.ExpressionConstants;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum BlackjackAction {
    HIT(ExpressionConstants.EXPRESSION_OF_HIT),
    STAY(ExpressionConstants.EXPRESSION_OF_STAY);

    private static Map<String, BlackjackAction> cachedBlackjackAction = Arrays.stream(values())
            .collect(Collectors.toMap(blackjackAction -> blackjackAction.expression, Function.identity()));

    private final String expression;

    BlackjackAction(final String expression) {
        this.expression = expression;
    }

    public static BlackjackAction from(final String expression) {
        if (cachedBlackjackAction.containsKey(expression)) {
            return cachedBlackjackAction.get(expression);
        }
        throw new IllegalArgumentException("일치하는 값이 없습니다.");
    }

    public boolean isHit() {
        return this == HIT;
    }
}
