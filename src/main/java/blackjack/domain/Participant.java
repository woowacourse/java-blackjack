package blackjack.domain;

public abstract class Participant {
    private final CardPocket cardPocket;

    protected Participant(final CardPocket cardPocket) {
        this.cardPocket = cardPocket;
    }

    public void drawCard(final Deck deck) {
        cardPocket.addCard(deck.popCard());
    }

    public int currentScore() {
        return cardPocket.calculateScore();
    }

    public abstract boolean isDrawable();
}
