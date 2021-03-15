package blackjack.domain.card;

import java.util.Arrays;

public enum Denomination {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private String name;
    private int score;

    Denomination(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public static Denomination of(String value) {
        return Arrays.stream(Denomination.values())
            .filter(denomination -> denomination.name.equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 명칭입니다."));
    }

    public boolean isAce() {
        return Denomination.ACE.equals(this);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
