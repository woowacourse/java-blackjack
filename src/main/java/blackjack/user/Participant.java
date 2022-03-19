package blackjack.user;

import blackjack.Card;
import blackjack.Cards;
import blackjack.State;

public abstract class Participant {
    private final String name;
    protected State state;
    protected Cards myCards;

    public Participant(String name) {
        this.name = name;
        this.state = State.HIT;
        this.myCards = Cards.generateEmpty();
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        myCards.addCard(card);
        updateStateAfterAddCard();
    }

    public boolean isHit() {
        return state.equals(State.HIT);
    }

    public boolean isBlackjack() {
        return state.equals(State.BLACKJACK);
    }

    public boolean isBust() {
        return state.equals(State.BUST);
    }

    protected void setStateBlackjackIfSatisfied() {
        if (2 == myCards.numberOfCards() && 21 == myCards.scoreSum()) {
            state = State.BLACKJACK;
        }
    }

    protected void setStateBustIfSatisfied() {
        if (myCards.scoreSum() > 21) {
            state = State.BUST;
        }
    }

    abstract void setStateStayIfSatisfied(boolean stayFlag);

    abstract void updateStateAfterAddCard();
}
