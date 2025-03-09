package domain;

import java.util.ArrayList;
import java.util.List;

public enum CardRank {
    ACE("A", List.of(1, 11)),
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
    KING("K", 10),
    ;

    private final String title;
    private final List<Integer> scores;

    CardRank(String title, int score) {
        this.title = title;
        scores = new ArrayList<>(List.of(score));
    }

    CardRank(String title, List<Integer> scores) {
        this.title = title;
        this.scores = scores;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public String getTitle() {
        return title;
    }
}
