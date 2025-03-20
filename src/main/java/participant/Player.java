package participant;

import card.Card;
import card.CardHand;
import card.GameScore;
import java.util.List;
import result.GameResult;
import result.Profit;
import state.State;
import state.running.PlayerHit;

public final class Player {
    private State state;
    private final Name name;
    private final Bet bet;

    public Player(final Name name, final Bet bet, final CardHand initialHand) {
        this.state = PlayerHit.initialState(initialHand);
        this.name = name;
        this.bet = bet;
    }

    public List<Card> openInitialCard() {
        CardHand cardHand = state.cardHand();
        return cardHand.getCards();
    }

    public void stay() {
        this.state = state.stay();
    }

    public Profit calculateProfit(final GameResult result) {
        return Profit.of(bet, result);
    }

    public Name getName() {
        return name;
    }

    public boolean isRunning() {
        return !state.isFinished();
    }

    public void receiveCard(Card newCard) {
        this.state = state.receiveCard(newCard);
    }

    public State getState() {
        return state;
    }

    public List<Card> getCards() {
        return state.cardHand().getCards();
    }

    public GameScore getScore() {
        return state.calculateScore();
    }
}
