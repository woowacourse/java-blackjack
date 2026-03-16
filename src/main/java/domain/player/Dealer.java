package domain.player;

import domain.card.Card;
import java.util.List;

public class Dealer extends Participant {
    public static final int HIT_BOUNDARY = 16;
    private static final String NAME = "딜러";

    private final Hand hand;

    private Dealer(Hand hand) {
        super(new Name(NAME));
        this.hand = hand;
    }

    public static Dealer from(Hand hand) {
        return new Dealer(hand);
    }

    @Override
    protected Hand getHand() {
        return hand;
    }

    public boolean needsToHit() {
        return hand.calculateTotalScore() <= HIT_BOUNDARY;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public List<Card> openFirstCard() {
        return List.of(hand.getCards().getFirst());
    }
}
