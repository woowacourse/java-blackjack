package model.card.dto;

import model.name.Name;

public class CardResponse {

    private final String name;
    private final int score;

    private CardResponse(final Name name, final int score) {
        this.name = name.getName();
        this.score = score;
    }

    public static CardResponse of(final Name name, final int score) {
        return new CardResponse(name, score);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
