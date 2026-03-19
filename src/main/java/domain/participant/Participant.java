package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Hand;
import java.util.List;
import domain.state.HandState;

public abstract class Participant {
    private final Hand hand;
    private Balance balance;

    protected Participant() {
        this.hand = new Hand();
        this.balance = Balance.ZERO;
    }

    public void drawCard(Cards cards) {
        hand.addCard(cards.draw());
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public void addCardForTest(final Card card) {
        hand.addCard(card);
    }

    public int getScore() {
        return hand.getScore();
    }

    public boolean checkBust() {
        return hand.checkBust();
    }

    public boolean isDealerDrawScore() {
        return hand.isDealerDrawScore();
    }

    public HandState getHandState(){
        return hand.getHandState();
    }

    public boolean canDraw() {
        return hand.canDraw();
    }

    protected void addBalance(int amount){
        this.balance = new Balance(balance.getBalance() + amount);
    }

    public int getBalance() {
        return balance.getBalance();
    }
}
