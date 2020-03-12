package domain.user;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public abstract class Player {
    private final Name name;
    private final Cards cards;

    public Player(final String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    public void addCard(final Card card) {
        cards.addCard(card);
    }

    public String getName() {
        return name.getValue();
    }

    public int getScore() {
        return cards.getScore();
    }

    public int getCardSize() {
        return cards.getSize();
    }

    public List<Card> getCards() {
        return cards.getValue();
    }

    public String getFirstCardInfo() {
        return cards.getFirstCardInfo();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public abstract boolean isWin(final Player player);
}
