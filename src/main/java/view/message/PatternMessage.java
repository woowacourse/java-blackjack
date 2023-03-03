package view.message;

import domain.card.CardPattern;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PatternMessage {
    HEART(CardPattern.HEART, "하트"),
    DIAMOND(CardPattern.DIAMOND, "다이아몬드"),
    CLOVER(CardPattern.CLOVER, "클로버"),
    SPADE(CardPattern.SPADE, "스페이드");

    private static final Map<CardPattern, String> CACHE = Stream.of(PatternMessage.values())
            .collect(Collectors.toUnmodifiableMap(
                    patternMessage -> patternMessage.pattern,
                    patternMessage -> patternMessage.message));

    private final CardPattern pattern;
    private final String message;

    PatternMessage(final CardPattern pattern, final String message) {
        this.pattern = pattern;
        this.message = message;
    }

    public static String findMessage(CardPattern pattern) {
        return CACHE.get(pattern);
    }
}
