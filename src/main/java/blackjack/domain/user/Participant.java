package blackjack.domain.user;

import blackjack.domain.Cards;
import blackjack.domain.Deck;
import blackjack.domain.State;

public abstract class Participant {

    private static final int INIT_CARD_NUM = 2;
    private static final int BLACKJACK_SCORE = 21;
    private static final int MAX_SCORE = 21;
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

    public Cards getCards() {
        return myCards;
    }

    public void drawInitialCards(Deck deck) {
        myCards.addCard(deck.pickTopCard());
        myCards.addCard(deck.pickTopCard());
        updateStateAfterAddCard();
    }

    public void drawAdditionalCard(Deck deck) {
        if (isHit()) {
            myCards.addCard(deck.pickTopCard());
            updateStateAfterAddCard();
        }
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

    public int score() {
        return myCards.scoreSum();
    }

    public State state() {
        return state;
    }

    protected void setStateBlackjackIfSatisfied() {
        if (INIT_CARD_NUM == myCards.numberOfCards() && BLACKJACK_SCORE == myCards.scoreSum()) {
            state = State.BLACKJACK;
        }
    }

    protected void setStateBustIfSatisfied() {
        if (myCards.scoreSum() > MAX_SCORE) {
            state = State.BUST;
        }
    }

    protected Cards openAllCards() {
        return myCards;
    }

    public abstract void setStateStayIfSatisfied(boolean stayFlag);

    abstract void updateStateAfterAddCard();

    public abstract Cards pickOpenCards();
}
