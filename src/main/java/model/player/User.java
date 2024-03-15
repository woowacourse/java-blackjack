package model.player;

import model.card.Card;
import model.card.Cards;

import java.awt.*;
import java.util.Objects;

public abstract class User {

    protected final Name name;
    protected final Cards cards;

    public User(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public int findPlayerDifference() {
        return cards.findPlayerDifference();
    }

    public boolean isNotHit() {
        return cards.isNotHit();
    }

    public boolean isHit() {
        return cards.isHit();
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
