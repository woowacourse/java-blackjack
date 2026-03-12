package team.blackjack.domain;

import java.util.List;
import team.blackjack.domain.rule.DefaultBlackjackRule;

public class Participant {
    private final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public int getScore() {
        return this.hand.getScore();
    }

    public void hit(Card card) {
        this.hand.addCard(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public boolean isBust() {
        return DefaultBlackjackRule.isBust(getScore());
    }
}
