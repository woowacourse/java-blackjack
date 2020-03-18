package domain.player;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public abstract class Player {
    private static final int BLACK_JACK_SCORE = 21;
    private static final int BLACK_JACK_SIZE = 2;

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

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public abstract boolean isWin(final Player player);

    public boolean isBlackJack() {
        return cards.getScore() == BLACK_JACK_SCORE && cards.getSize() == BLACK_JACK_SIZE;
    }
}
