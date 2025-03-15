package participant;

import card.Card;
import card.Deck;
import card.Hand;

public class Dealer extends Participant {
    private static final int VALID_DRAW_LIMIT = 16;

    public Dealer(Hand hand) {
        super(hand);
    }

    public static Dealer createWithNoHand() {
        return new Dealer(Hand.createEmpty());
    }

    public boolean shouldDrawCard() {
        return getScore().isLessOrEqualThen(VALID_DRAW_LIMIT);
    }

    @Override
    public Hand openHand() {
        return Hand.from(hand.getFirst());
    }

    @Override
    public Participant initializeHandWith(Hand updatedHand) {
        return new Dealer(updatedHand);
    }

    @Override
    public Participant updateHandWith(Card card) {
        return new Dealer(hand.add(card));
    }

    public Dealer drawCardIfNeeded(Deck deck) {
        if (shouldDrawCard()) {
            return (Dealer) this.updateHandWith(deck.drawCard());
        }
        return this;
    }
}
