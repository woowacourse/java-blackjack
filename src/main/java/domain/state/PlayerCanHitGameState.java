package domain.state;

import domain.Card;
import domain.Hand;
import java.util.function.Supplier;

public class PlayerCanHitGameState extends CanHitGameState {
    public PlayerCanHitGameState(Hand hand) {
        super(hand);
    }

    @Override
    public GameState hit(Supplier<Card> cardSupplier) {
        Hand newHand = hand.addCard(cardSupplier.get());
        if (newHand.isBust()) {
            return new BustGameState(newHand);
        }
        return new PlayerCanHitGameState(newHand);
    }

    @Override
    public GameState stay() {
        return new StayGameState(hand);
    }
}
