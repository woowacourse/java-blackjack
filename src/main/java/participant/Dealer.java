package participant;

import card.Card;
import card.CardHand;
import card.GameScore;
import java.util.List;
import result.GameResult;
import state.State;
import state.running.DealerHit;

public final class Dealer {
    private State state;

    public Dealer(final CardHand initialHand) {
        this.state = DealerHit.initialState(initialHand);
    }

    public List<Card> openInitialCard() {
        CardHand cardHand = state.cardHand();
        return List.of(cardHand.getFirstCard());
    }

    public GameResult judgeResult(final Player player) {
        State playerState = player.getState();
        return playerState.calculatePlayerResult(this.state);
    }

    public boolean isRunning() {
        return !state.isFinished();
    }

    public void receiveCard(Card newCard) {
        this.state = state.receiveCard(newCard);
    }

    public List<Card> getCards() {
        return state.cardHand().getCards();
    }

    public GameScore getScore() {
        return state.calculateScore();
    }
}
