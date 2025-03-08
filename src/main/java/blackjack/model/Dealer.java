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

    public Card drawCard() {
        return deck.draw();
    }

    @Override
    public boolean shouldHit() {
        return getTotal() <= DEALER_HIT_THRESHOLD;
    }

    public Card getVisibleCard() {
        if (hand.isEmpty()) {
            throw new IllegalStateException("딜러가 가진 패가 없습니다.");
        }
        return hand.getFirst();
    }
}
