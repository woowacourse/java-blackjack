package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.Deck;
import java.util.ArrayList;

public final class Dealer extends Participant {

    public static final int DEALER_HIT_THRESHOLD = 16;

    private final Deck deck;

    public Dealer(Deck deck) {
        super(new ArrayList<>());
        this.deck = deck;
    }

    @Override
    public boolean canHit() {
        return getTotal() <= DEALER_HIT_THRESHOLD;
    }

    public void dealCard(Participant participant) {
        participant.receiveHand(drawCard());
    }

    private Card drawCard() {
        return deck.draw();
    }

    public Card getVisibleCard() {
        if (hand.isEmpty()) {
            throw new IllegalStateException("딜러가 가진 패가 없습니다.");
        }
        return hand.getFirst();
    }
}
