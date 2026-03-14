package domain.state;

import domain.Card;
import domain.Hand;
import java.util.List;

public abstract class StartedGameState implements GameState {
    protected final Hand hand;

    protected StartedGameState(Hand hand) {
        this.hand = hand;
    }

    @Override
    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    @Override
    public boolean isBust() {
        return hand.isBust();
    }

    @Override
    public List<Card> showOwnCards() {
        return hand.showCards();
    }

    @Override
    public int getCardsSum() {
        return hand.calculateCardScoreSum();
    }
}
