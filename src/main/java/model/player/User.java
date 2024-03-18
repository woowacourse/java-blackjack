package model.player;

import model.card.Card;
import model.card.Cards;

public abstract class User {

    protected final Cards cards;

    public User(Cards cards) {
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

    public Cards cards() {
        return cards;
    }
}
