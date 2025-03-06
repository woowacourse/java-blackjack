package blackjack.domain;

import java.util.List;

public abstract class Participant {
    private static final int TARGET_SCORE = 21;

    protected final CardDeck cardDeck;
    protected final CardDump cardDump;

    public Participant(CardDeck cardDeck, CardDump cardDump) {
        this.cardDeck = cardDeck;
        this.cardDump = cardDump;
    }

    abstract boolean canTakeExtraCard();

    public abstract int calculateTotalCardScore();

    public boolean isBust() {
        return calculateTotalCardScore() > TARGET_SCORE;
    }

    public void addCard() {
        cardDeck.add(cardDump.drawCard());
    }

    public List<Card> getCardDeck() {
        return cardDeck.getCards();
    }

    public abstract String getName();
}
