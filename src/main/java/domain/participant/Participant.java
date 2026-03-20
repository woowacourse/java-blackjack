package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import java.util.List;
import java.util.function.Supplier;

public abstract class Participant {

    protected final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public void drawInitialCards(Supplier<Card> cardSupplier) {
        drawCard(cardSupplier.get());
        drawCard(cardSupplier.get());
    }

    public void drawCard(Card card) {
        hand.drawCard(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public int calculateScore() {
        return hand.calculateTotalScore();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public List<Card> getHand() {
        return hand.getCard();
    }

    public abstract String getName();
}
