package domain;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class Dealer extends Participant {
    private static final int MINIMUM_TOTAL_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    private Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    public static Dealer from(Hand hand) {
        return new Dealer(hand);
    }

    @Override
    public List<Card> showInitialCard() {
        List<Card> ownCards = super.showOwnCards();
        return List.of(ownCards.getFirst());
    }

    public Optional<Dealer> addCard(Supplier<Card> cardSupplier) {
        if (hand.calculateCardScoreSum() <= MINIMUM_TOTAL_SCORE) {
            Hand newHand = this.hand.addCard(cardSupplier.get());
            return Optional.of(new Dealer(newHand));
        }
        return Optional.empty();
    }
}
