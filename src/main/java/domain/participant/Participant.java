package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import domain.rule.BlackjackRule;
import domain.rule.GameRule;

public class Participant {
    private final Hand hand;
    protected final GameRule rule;

    protected Participant(Hand hand) {
        this.hand = hand;
        this.rule = new BlackjackRule();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int getCardScore() {
        return rule.getScore(hand);
    }

    public boolean isBurst() {
        return rule.isBurst(hand);
    }

    public Hand getCards() {
        return hand;
    }
}
