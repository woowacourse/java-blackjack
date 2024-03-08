package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.card.TrumpCard;
import blackjack.util.Constants;

public class Dealer extends Gamer {

    private final Deck deck;

    public Dealer(final Deck deck) {
        super();

        this.deck = deck;

        selfDraw();
        selfDraw();
    }

    public TrumpCard draw() {
        return deck.drawn();
    }

    public boolean extraCard() {
        if (canReceiveCard()) {
            selfDraw();
            return true;
        }
        return false;
    }

    @Override
    boolean canReceiveCard() {
        return hand.calculateScore() <= Constants.DEALER_BOUND;
    }

    private void selfDraw() {
        TrumpCard trumpCard = this.deck.drawn();
        hand.add(trumpCard);
    }

    public TrumpCard showFirstCard() {
        return hand.getFirstCard();
    }

    public boolean isSameScore(final long score) {
        return hand.calculateScore() == score;
    }
}
