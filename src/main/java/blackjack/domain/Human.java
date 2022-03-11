package blackjack.domain;

import java.util.List;

public abstract class Human {

    protected final PlayingCards playingCards = new PlayingCards();

    public List<Card> showCards() {
        return playingCards.getAllCards();
    }

    public int showSumOfCards() {
        return playingCards.calculateTotal();
    }

    public void drawInitCards(final List<Card> initCards) {
        playingCards.add(initCards);
    }

    public void drawCard(final Card card) {
        playingCards.add(card);
    }

    public boolean isBust() {
        return playingCards.isOverBlackjack();
    }

    abstract boolean isDrawable();
}
