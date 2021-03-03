package blackjack.domain;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {
    protected List<Card> cards;

    public User() {
        this.cards = new ArrayList<>();
    }

    public abstract void hit(Card card);

    public abstract boolean isStay();

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
