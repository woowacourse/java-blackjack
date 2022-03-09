package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Gamer {
    private static final String NAME_INPUT_ERROR_MESSAGE = "참가자의 이름으로 공백이나 빈 문자열은 입력할 수 없습니다.";

    private final String name;
    private final Cards cards;

    public Gamer(String name, Cards cards) {
        validate(name);
        draw();
        draw();
        this.name = name;
        this.cards = cards;
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(NAME_INPUT_ERROR_MESSAGE);
        }
    }


    public void draw() {
        cards.add(Card.draw());
    }

    public boolean isBust() {
        return cards.calculateScore() > 21;
    }

    public abstract void hit();

    public  Cards getCards() {
        return cards;
    }
}
