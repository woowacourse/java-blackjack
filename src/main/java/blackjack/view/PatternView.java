package blackjack.view;

import blackjack.domain.card.Pattern;

import java.util.Arrays;

public enum PatternView {

    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String patternName;

    PatternView(String patternName) {
        this.patternName = patternName;
    }

    public static String convertPatternName(Pattern pattern) {
        return Arrays.stream(PatternView.values())
                .filter(patternView -> patternView.name().equals(pattern.name()))
                .findFirst()
                .orElseThrow()
                .patternName;
    }
}
