package domain.state;

import domain.Card;
import domain.Hand;
import java.util.function.Supplier;

public class DealerCanHitGameState extends CanHitGameState {
    private static final int MINIMUM_TOTAL_SCORE = 16;

    public DealerCanHitGameState(Hand hand) {
        super(hand);
    }

    @Override
    public GameState hit(Supplier<Card> cardSupplier) {
        Hand newHand = this.hand.addCard(cardSupplier.get());
        if (newHand.isBust()) {
            return new BustGameState(newHand);
        }

        if (newHand.calculateCardScoreSum() > MINIMUM_TOTAL_SCORE) {
            return new StayGameState(newHand);
        }

        return new DealerCanHitGameState(newHand);
    }

    @Override
    public GameState stay() {
        if (hand.calculateCardScoreSum() > MINIMUM_TOTAL_SCORE) {
            return new StayGameState(hand);
        }
        return this;
    }
}
