package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Pattern {
    HEART("하트"),
    SPADE("스페이드"),
    CLUB("클로버"),
    DIAMOND("다이아몬드");

    private final String name;

    Pattern(final String name) {
        this.name = name;
    }

    public static Pattern pickRandomPattern() {
        List<Pattern> patterns = Arrays.asList(Pattern.values());
        Collections.shuffle(patterns);

        return patterns.get(0);
    }
}
