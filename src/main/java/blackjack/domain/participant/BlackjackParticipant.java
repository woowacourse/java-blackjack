package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import java.util.List;

public abstract class BlackjackParticipant implements Decidable {

    protected Participant participant;

    public BlackjackParticipant(Participant participant) {
        this.participant = participant;
    }

    public void receiveCard(final Card card) {
        participant.receiveCard(card);
    }

    public Score calculateTotalScore() {
        return participant.calculateTotalScore();
    }

    public boolean isBust() {
        return participant.isBust();
    }

    public boolean isBlackjack() {
        return participant.isBlackjack();
    }

    public List<Card> getHand() {
        return participant.getHand();
    }

    public String getName() {
        return participant.getName();
    }
}
