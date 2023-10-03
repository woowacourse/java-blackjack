package model.card;

import model.card.dto.CardRequest;
import model.name.Name;

import static util.Rule.MIN_SCORE;

public class Card {

    private final Name name;
    private int score;

    private Card(final Name name, final int score) {
        this.name = name;
        this.score = score;
    }

    public static Card from(final CardRequest request) {
        return new Card(Name.from(request.getName()), request.getScore());
    }

    public void downScore() {
        this.score = MIN_SCORE.getValue();
    }

    public Name getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
