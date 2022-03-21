package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected String name;
    protected State state;

    public Participant() {
        this.state = new Ready(new ParticipantCards());
    }

    public void receiveInitCards(List<Card> cards) {
        for (Card card : cards) {
            state = state.draw(card);
        }
    }

//    public void receiveCard(Card card) {
//        state = state.draw(card);
//    }
    public void draw(Card card) {
        state = state.draw(card);
    }

    public void stay() {
        state = state.stay();
    }

    public boolean isBust() {
        return state.isBust();
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public int getScore() {
        return state.participantCards().calculateScore();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(state.participantCards().getCards());
    }

    public boolean isHittable() {
        return state.isFinished();
    }

    public abstract String getName();

}
