package domain.state;

import common.ErrorMessage;
import domain.Card;
import domain.Hand;
import java.util.function.Supplier;

public abstract class EndGameState extends CommonGameState {
    public EndGameState(Hand hand) {
        super(hand);
    }

    @Override
    public GameState hit(Supplier<Card> cardSupplier) {
        throw new IllegalStateException(ErrorMessage.NOT_ALLOW_METHOD_CALL.getMessage());
    }

    @Override
    public GameState stay() {
        throw new IllegalStateException(ErrorMessage.NOT_ALLOW_METHOD_CALL.getMessage());
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isPlayable() {
        return false;
    }
}
