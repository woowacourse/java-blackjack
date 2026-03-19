package domain.state;

import domain.Card;
import domain.Hand;
import java.util.List;
import java.util.function.Supplier;

public interface GameState {
    static GameState createPlayerInitialGameState(Hand hand) {
        return createInitialGameState(hand);
    }

    static GameState createDealerInitialGameState(Hand hand) {
        if (hand.isDealerFull()) {
            return new StayGameState(hand);
        }
        return createInitialGameState(hand);
    }

    private static GameState createInitialGameState(Hand hand) {
        if (hand.isBlackJack()) {
            return new BlackJackGameState(hand);
        }
        return new RunningGameState(hand);
    }

    GameState hit(Supplier<Card> cardSupplier);

    GameState stay();

    boolean isPlayable();

    boolean isFinished();

    boolean isBlackJack();

    boolean isBust();

    List<Card> showOwnCards();

    int getCardsSum();
}