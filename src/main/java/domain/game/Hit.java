package domain.game;

import domain.card.Card;
import domain.card.Hand;
import java.util.List;

public class Hit implements HandState {

    private final Hand hand;

    public Hit(Hand hand) {
        this.hand = hand;
    }

    @Override
    public Outcome versus(HandState other) {
        return new Stay(hand).versus(other);
    }

    @Override
    public boolean canHit() {
        return true;
    }

    @Override
    public HandState draw(Card card) {
        hand.addCard(card);
        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        if (hand.isBust()) {
            return new Bust(hand);
        }
        return this;
    }

    @Override
    public HandState stay() {
        return new Stay(hand);
    }

    @Override
    public List<Card> cards() {
        return hand.getCards();
    }

    @Override
    public int score() {
        return hand.calculateScore();
    }
}
