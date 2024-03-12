package blackjack.participant.state;

import blackjack.card.Card;
import blackjack.game.MatchResult;
import blackjack.participant.Hand;
import blackjack.participant.Score;
import java.util.List;

public abstract class GameState {

    protected Hand hand;

    protected GameState(Hand hand) {
        this.hand = hand;
    }

    public abstract GameState drawCard(Card card);

    public abstract GameState stand();

    public abstract boolean isTerminated();

    public abstract MatchResult createMatchResult(GameState dealerState);

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public List<Card> cards() {
        return hand.getCards();
    }

    public Score getScore() {
        return hand.getScore();
    }
}
