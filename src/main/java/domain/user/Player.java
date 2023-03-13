package domain.user;

import domain.card.Card;
import domain.card.Score;
import domain.state.State;

import java.util.List;

public class Player {

    private final Participant participant;
    private final int betting;

    public Player(String PlayerName, final int betting) {
        this.participant = new Participant(PlayerName);
        this.betting = betting;
    }

    public State hit(Card card) {
        return participant.hit(card);
    }

    public State stay() {
        return participant.stay();
    }

    public int calculate() {
        State state = getState();
        return (int) state.profit(betting);
    }

    public int calculateStay(Dealer dealer) {
        if (getScore().isMoreThan(dealer.getScore())) {
            return calculate();
        }
        if (getScore().equals(dealer.getScore())) {
            return 0;
        }

        return -betting;
    }

    public Score getScore() {
        return getState().score();
    }

    public String name() {
        return participant.name();
    }

    public State getState() {
        return participant.getState();
    }

    public List<Card> cards() {
        return getState().cards();
    }
}
