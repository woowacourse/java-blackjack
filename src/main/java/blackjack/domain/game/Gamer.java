package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.List;

public abstract class Gamer {

    protected final String name;
    protected final PlayingCards playingCards = new PlayingCards();

    protected Gamer(final String name) {
        this.name = name;
    }

    public List<Card> openCards() {
        return playingCards.getAllCards();
    }

    public int sumOfCards() {
        return playingCards.calculateTotal();
    }

    public void dealCards(final List<Card> cards) {
        playingCards.add(cards);
    }

    public void drawCard(final Card card) {
        playingCards.add(card);
    }

    public boolean isBlackjack() {
        return playingCards.isBlackjack();
    }

    public boolean isBust() {
        return playingCards.isOverBlackjack();
    }

    abstract boolean isDrawable();
}
