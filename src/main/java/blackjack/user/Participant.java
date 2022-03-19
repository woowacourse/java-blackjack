package blackjack.user;

import blackjack.Card;
import blackjack.Cards;
import blackjack.Deck;
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

    public void drawInitialCards(Deck deck) {
        myCards.addCard(deck.pickTopCard());
        myCards.addCard(deck.pickTopCard());
        updateStateAfterAddCard();
    }

    public void drawAdditionalCard(Deck deck) {
        myCards.addCard(deck.pickTopCard());
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

    protected Cards openAllCards() {
        return myCards;
    }

    abstract void setStateStayIfSatisfied(boolean stayFlag);

    abstract void updateStateAfterAddCard();

    abstract Cards pickOpenCards();
}
