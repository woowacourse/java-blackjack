package view.message;

import controller.DrawCardCommand;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DrawCardCommandSelector {

    CARD_DRAW_AGAIN("y", DrawCardCommand.CARD_DRAW_AGAIN),
    CARD_DRAW_STOP("n", DrawCardCommand.CARD_DRAW_STOP);

    private final String message;
    private final DrawCardCommand drawable;

    private static final Map<String, DrawCardCommand> CACHE = Stream.of(DrawCardCommandSelector.values())
            .collect(Collectors.toUnmodifiableMap(formatter -> formatter.message,
                    formatter -> formatter.drawable));

    DrawCardCommandSelector(final String message, final DrawCardCommand drawable) {
        this.message = message;
        this.drawable = drawable;
    }

    public static DrawCardCommand findDrawable(final String command) {
        try {
            return CACHE.get(command);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(getExceptionMessage(), e);
        }
    }

    private static String getExceptionMessage() {
        return String.format("%s 혹은 %s를 입력해 주세요.", CARD_DRAW_AGAIN.message, CARD_DRAW_STOP.message);
    }

    public String command() {
        return message;
    }
}
