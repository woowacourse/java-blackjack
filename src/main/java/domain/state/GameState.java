package domain.state;

import domain.Card;
import domain.GameResult;
import domain.Hand;
import java.util.List;
import java.util.function.Supplier;

public interface GameState {
    static GameState createPlayerInitialGameState(Hand hand) {
        if (hand.isBlackJack()) {
            return new BlackJackGameState(hand);
        }
        return new PlayerCanHitGameState(hand);
    }

    static GameState createDealerInitialGameState(Hand hand) {
        if (hand.isBlackJack()) {
            return new BlackJackGameState(hand);
        }
        return new DealerCanHitGameState(hand);
    }

    GameState hit(Supplier<Card> cardSupplier);

    GameState stay();

    boolean isBlackJack();

    boolean isBust();

    boolean isFinished();

    List<Card> showOwnCards();

    int getCardsSum();

    GameResult compare(GameState playerState, GameState dealerState);
}