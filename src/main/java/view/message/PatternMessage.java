package view.message;

import domain.card.Shape;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PatternMessage {
    HEART(Shape.HEART, "하트"),
    DIAMOND(Shape.DIAMOND, "다이아몬드"),
    CLOVER(Shape.CLOVER, "클로버"),
    SPADE(Shape.SPADE, "스페이드");

    private static final Map<Shape, String> CACHE = Stream.of(PatternMessage.values())
            .collect(Collectors.toUnmodifiableMap(patternMessage -> patternMessage.pattern,
                    patternMessage -> patternMessage.message));

    private final Shape pattern;
    private final String message;

    PatternMessage(final Shape pattern, final String message) {
        this.pattern = pattern;
        this.message = message;
    }

    public static String findMessage(Shape pattern) {
        return CACHE.get(pattern);
    }
}
