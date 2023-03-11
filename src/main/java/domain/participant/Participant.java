package domain.participant;

import domain.card.Card;

import domain.game.GameResult;
import java.math.BigDecimal;
import java.util.List;

public abstract class Participant {

    protected static final String DEALER_NAME = "딜러";

    protected ParticipantCard participantCard;

    protected Participant() {
        participantCard = ParticipantCard.create();
    }

    public final void addCard(final Card card) {
        this.participantCard = this.participantCard.addCard(card);
    }

    public final int calculateScore() {
        final ParticipantScore participantScore = participantCard.calculateScore();

        return participantScore.score();
    }

    public abstract GameResult calculateResult(Participant participant);

    public abstract BigDecimal calculateBenefit(final GameResult gameResult);

    public abstract boolean canDraw();

    public final List<Card> getCard() {
        return List.copyOf(participantCard.getCards());
    }

    public abstract List<Card> getStartCard();

    public abstract String getName();
}
