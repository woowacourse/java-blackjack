package blackjack.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Suit {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String koreanName;

    private static final Map<String, Suit> SUIT_MAP = Arrays.stream(values())
            .collect(Collectors.toMap(suit -> suit.koreanName, suit -> suit));

    Suit(String koreanName) {
        this.koreanName = koreanName;
    }

    public static Suit of(String koreanName) {
        return Optional.ofNullable(SUIT_MAP.get(koreanName))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드 이름입니다."));
    }
}

