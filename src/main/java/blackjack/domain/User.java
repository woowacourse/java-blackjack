package blackjack.domain;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {
    protected String name;
    protected List<Card> cards;

    public User() {
        this.cards = new ArrayList<>();
    }

    public abstract void hit(Card card);

    public abstract boolean isStay();

    public int getScore() {
        return this.cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public String getName() {
        return this.name;
    }
}
