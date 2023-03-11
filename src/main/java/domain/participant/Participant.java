package domain.participant;

import domain.card.Card;

import domain.game.GameResult;
import java.math.BigDecimal;
import java.util.List;

public abstract class Participant {

    protected static final String DEALER_NAME = "딜러";

    private final ParticipantName name;
    protected ParticipantCard participantCard;

    protected Participant(final String name) {
        this.name = ParticipantName.create(name);

        participantCard = ParticipantCard.create();
    }

    public final void addCard(final Card card) {
        this.participantCard = this.participantCard.addCard(card);
    }

    public final int calculateScore() {
        final ParticipantScore participantScore = participantCard.calculateScore();

        return participantScore.score();
    }

    public abstract boolean canDraw();

    public abstract List<Card> getStartCard();

    public final List<Card> getCard() {
        return List.copyOf(participantCard.getCards());
    }

    public String getName() {
        return name.getName();
    }
}
