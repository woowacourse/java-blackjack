package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardScore;
import blackjack.model.state.State;

import java.util.List;

public abstract class Participant {
    protected Name name;
    protected State currentState;

    public Participant(Name name, State currentState) {
        this.name = name;
        this.currentState = currentState;
    }

    abstract public void play(CardDeck cardDeck);

    public boolean isBlackjack() {
        return currentState.isBlackjack();
    }

    public boolean isBust() {
        return currentState.isBust();
    }

    public boolean isStand() {
        return currentState.isStand();
    }

    public boolean isFinished() {
        return currentState.isFinished();
    }

    public CardScore cardScore() {
        return currentState.getScore();
    }

    public int getScore() {
        if (isBust()) {
            return cardScore().smallScore();
        }
        return cardScore().bigScore();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return currentState.getHand();
    }
}


