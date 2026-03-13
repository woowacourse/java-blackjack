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
        return new PlayerPlayableGameState(hand);
    }

    static GameState createDealerInitialGameState(Hand hand) {
        if (hand.isBlackJack()) {
            return new BlackJackGameState(hand);
        }
        return new DealerPlayableGameState(hand);
    }

    GameState hit(Supplier<Card> cardSupplier);

    GameState stay();

    boolean isPlayable();

    boolean isFinished();

    boolean isBlackJack();

    boolean isBust();

    List<Card> showOwnCards();

    int getCardsSum();

    GameResult compare(GameState playerState, GameState dealerState);
}