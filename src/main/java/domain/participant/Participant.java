package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import domain.rule.BlackjackRule;
import domain.rule.GameRule;
import java.util.Objects;

public abstract class Participant {

    private final Hand hand;
    protected final GameRule rule;

    protected Participant(Hand hand) {
        this.hand = hand;
        this.rule = BlackjackRule.getInstance();
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

    public boolean isBlackJack() {
        return rule.isBlackJack(hand);
    }

    public Hand getCards() {
        return hand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(hand, that.hand) && Objects.equals(rule, that.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hand, rule);
    }
}
