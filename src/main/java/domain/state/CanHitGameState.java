package domain.state;

import domain.Card;
import domain.Hand;
import java.util.function.Supplier;

public abstract class CanHitGameState extends CommonGameState {

    public CanHitGameState(Hand hand) {
        super(hand);
    }

    @Override
    public abstract GameState hit(Supplier<Card> cardSupplier);

    @Override
    public abstract GameState stay();

    @Override
    public boolean isFinished() {
        return false;
    }
}