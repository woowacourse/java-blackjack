package domain.state;

import domain.Card;
import domain.GameResult;
import domain.Hand;
import java.util.List;
import java.util.function.Supplier;

public abstract class CommonGameState implements GameState {
    protected final Hand hand;

    public CommonGameState(Hand hand) {
        this.hand = hand;
    }

    @Override
    public abstract GameState hit(Supplier<Card> cardSupplier);

    @Override
    public abstract GameState stay();

    @Override
    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    @Override
    public boolean isBust() {
        return hand.isBust();
    }

    @Override
    public abstract boolean isFinished();

    @Override
    public List<Card> showOwnCards() {
        return hand.showCards();
    }

    @Override
    public int getCardsSum() {
        return hand.calculateCardScoreSum();
    }

    @Override
    public GameResult compare(GameState playerState, GameState dealerState) {
        return GameResult.decidePlayerResult(playerState, dealerState);
    }
}
