package domain.state;

import domain.Card;
import domain.Hand;
import java.util.function.Supplier;

public class PlayerPlayableGameState extends PlayableGameState {
    public PlayerPlayableGameState(Hand hand) {
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

        return new PlayerPlayableGameState(newHand);
    }

    @Override
    public GameState stay() {
        return new StayGameState(hand);
    }
}
