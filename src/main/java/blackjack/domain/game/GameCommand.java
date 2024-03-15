package blackjack.domain.game;

import blackjack.utils.Constants;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum GameCommand {
    YES(Constants.EXPRESSION_OF_YES),
    NO(Constants.EXPRESSION_OF_NO);

    private static Map<String, GameCommand> cachedGameCommand = Arrays.stream(values())
            .collect(Collectors.toMap(gameCommand -> gameCommand.expression, Function.identity()));

    private final String expression;

    GameCommand(final String expression) {
        this.expression = expression;
    }

    public static GameCommand from(final String expression) {
        if (cachedGameCommand.containsKey(expression)) {
            return cachedGameCommand.get(expression);
        }
        throw new IllegalArgumentException("일치하는 값이 없습니다.");
    }

    public boolean isYes() {
        return this == YES;
    }

    public boolean isNo() {
        return this == NO;
    }
}
