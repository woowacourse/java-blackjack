package blackjack.model.participant;

import static blackjack.model.RuleConstants.DEALER_HIT_THRESHOLD;

import blackjack.model.card.Card;
import blackjack.model.card.Deck;
import java.util.ArrayList;

public class Dealer extends Participant {

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
