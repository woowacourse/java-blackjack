package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Score;
import java.util.List;

public class Dealer {

    public static final int MAX_RECEIVABLE_SCORE = 16;

    private final Hand hand;

    private Dealer(Hand hand) {
        this.hand = new Hand();
    }

    public static Dealer withNoCards() {
        return new Dealer(new Hand());
    }

    public void tryReceive(Card card) {
        if (isReceivable()) {
            hand.add(card);
        }
    }

    public void tryReceive(List<Card> cards) {
        if (isReceivable()) {
            hand.add(cards);
        }
    }

    public boolean isBust() {
        return hand.totalScore().isBust();
    }

    public Score score() {
        return hand.totalScore();
    }

    public Card firstCard() {
        return hand.getCards()
            .get(0);
    }

    public boolean isReceivable() {
        return score().toInt() <= MAX_RECEIVABLE_SCORE;
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
