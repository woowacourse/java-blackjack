package model.card.dto;

import model.name.Name;

public class CardRequest {

    private final Name name;
    private final int score;

    private CardRequest(final Name name, final int score) {
        this.name = name;
        this.score = score;
    }

    public static CardRequest createDefault(final Name name, final int score) {
        return new CardRequest(name, score);
    }

    public Name getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
