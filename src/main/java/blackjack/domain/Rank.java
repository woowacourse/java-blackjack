package blackjack.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Rank {
    ACE("A", 11),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    QUEEN("Q", 10),
    JACK("J", 10),
    KING("K", 10);

    private static final Map<String, Rank> RANK_MAP = Arrays.stream(values())
            .collect(Collectors.toMap(rank -> rank.symbol, rank -> rank));
    private final String symbol;
    private final int score;

    Rank(String symbol, int score) {
        this.symbol = symbol;
        this.score = score;
    }

    public static Rank of(String symbol) {
        return Optional.ofNullable(RANK_MAP.get(symbol))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드 이름입니다."));
    }

    public String getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }
}
