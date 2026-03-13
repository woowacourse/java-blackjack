package domain.state;

import domain.Card;
import domain.Hand;
import java.util.function.Supplier;

public abstract class PlayableGameState extends CommonGameState {

    public PlayableGameState(Hand hand) {
        super(hand);
    }

    @Override
    public abstract GameState hit(Supplier<Card> cardSupplier);

    @Override
    public abstract GameState stay();

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}