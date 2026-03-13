package domain.state;

import domain.Card;
import domain.Hand;
import java.util.function.Supplier;

public class RunningGameState extends StartedGameState {

    public RunningGameState(Hand hand) {
        super(hand);
    }

    @Override
    public GameState hit(Supplier<Card> cardSupplier) {
        Hand newHand = hand.addCard(cardSupplier.get());
        if (newHand.isBust()) {
            return new BustGameState(newHand);
        }

        if (newHand.isBlackJack()) {
            return new BlackJackGameState(newHand);
        }

        if (newHand.isFull()) {
            return new StayGameState(newHand);
        }

        return new RunningGameState(newHand);
    }

    @Override
    public GameState stay() {
        return new StayGameState(hand);
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
