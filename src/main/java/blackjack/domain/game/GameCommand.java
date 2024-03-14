package blackjack.domain.game;

import blackjack.utils.Constants;

import java.util.Arrays;

public enum GameCommand {
    YES(Constants.EXPRESSION_OF_YES),
    NO(Constants.EXPRESSION_OF_NO);

    private final String expression;

    GameCommand(final String expression) {
        this.expression = expression;
    }

    public static GameCommand from(final String expression) {
        return Arrays.stream(values())
                .filter(gameCommand -> gameCommand.isExpressionMatch(expression))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 값이 없습니다."));
    }

    private boolean isExpressionMatch(final String expression) {
        return this.expression.equals(expression);
    }
}
