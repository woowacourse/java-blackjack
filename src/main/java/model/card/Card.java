package model.card;

import model.card.dto.CardRequest;
import model.name.Name;

public class Card {

    private final Name name;
    private final int score;

    private Card(final Name name, final int score) {
        this.name = name;
        this.score = score;
    }

    public static Card from(final CardRequest request) {
        return new Card(request.getName(), request.getScore());
    }
}
