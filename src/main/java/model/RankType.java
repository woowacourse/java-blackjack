package model;

import java.util.List;

public enum RankType {

    TWO("2",2),
    THREE("3",3),
    FOUR("4",4),
    FIVE("5",5),
    SIX("6",6),
    SEVEN("7",7),
    EIGHT("8",8),
    NINE("9",9),
    TEN("10",10),
    KING("K",10),
    JACK("J",10),
    QUEEN("Q",10),
    ACE("A",List.of(1,11));

    private final String displayName;
    private final List<Integer> score;

    RankType(String displayName, int score) {
        this.displayName = displayName;
        this.score = List.of(score);
    }

    RankType(String displayName, List<Integer> score) {
        this.displayName = displayName;
        this.score = score;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<Integer> getScore() {
        return score;
    }
}
