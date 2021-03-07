package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Person {

    private static final int MIN_NAME_LENGTH = 1;

    protected Cards cards;
    protected String name;

    public Person(String name) {
        validateNameLength(name);
        this.name = name;
        this.cards = new Cards();
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException("유효하지 않은 이름입니다.");
        }
    }

    public void receiveCard(Card card) {
        this.cards = cards.addCard(card);
    }

    public Cards getTakenCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canDraw();
}
