package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Hand;
import java.util.List;
import domain.state.HandState;

public abstract class Participant {
    private final Hand hand;

    protected Participant() {
        this.hand = new Hand();
    }

    public void drawCard(Cards cards) {
        hand.addCard(cards.draw());
    }

    public List<Card> getCardList() {
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
}
