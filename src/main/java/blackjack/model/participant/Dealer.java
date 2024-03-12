package blackjack.model.participant;

import blackjack.model.deck.Card;
import blackjack.model.deck.Deck;
import java.util.List;

public class Dealer extends Participant {
    private static final int HITTABLE_THRESHOLD = 16;

    private Deck deck;

    public Dealer(final Hand hand) {
        this.hand = hand;
    }

    // TODO: HAND 없애기
    public Dealer(final Hand hand, final Deck deck) {
        this.hand = hand;
        this.deck = deck;
    }

    @Override
    public boolean canHit() {
        return hand.calculateScore() <= HITTABLE_THRESHOLD;
    }

    public List<Card> distributeInitialCard() {
        return List.of(deck.distribute(), deck.distribute());
    }

    public Card distributeCard() {
        return deck.distribute();
    }

    public List<Card> openFirstCard() {
        return List.of(hand.getFirstCard());
    }

    // TODO: getName 없애기
    public String getName() {
        return "딜러";
    }
}
