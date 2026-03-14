package domain.state;

import domain.Card;
import domain.Hand;
import java.util.List;
import java.util.function.Supplier;

public interface GameState {
    static GameState createInitialGameState(Hand hand) {
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