package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.state.Hand;
import blackjack.domain.state.ParticipantState;
import java.util.List;

public abstract class Participant {
    private final Name name;
    private final ParticipantState participantState;

    public Participant(Name name, ParticipantState participantState) {
        this.name = name;
        this.participantState = participantState;
    }

    public abstract Participant draw(Deck deck);

    public abstract Participant stand();

    public final Score calculateHand() {
        return participantState.calculateHand();
    }

    public boolean canDraw() {
        return !participantState.isFinished();
    }

    final ParticipantState drawHand(Deck deck) {
        return participantState.draw(deck);
    }

    final ParticipantState standHand() {
        return participantState.stand();
    }

    final double calculateProfitRate(Hand other) {
        return participantState.getProfitRate(other);
    }

    final Hand getHand() {
        return participantState.getHand();
    }

    public final Name getName() {
        return name;
    }

    final ParticipantState getState() {
        return participantState;
    }

    public final boolean isFinished() {
        return participantState.isFinished();
    }

    public final List<Card> getCards() {
        return participantState.getHand().getCards();
    }
}
