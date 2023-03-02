package domain.participant;

import domain.card.Card;

import java.util.List;

public abstract class Participant {

    protected static final String DEALER_NAME = "딜러";
    private final ParticipantName name;
    protected final ParticipantCard participantCard;

    protected Participant(final String name) {
        this.name = ParticipantName.create(name);
        participantCard = ParticipantCard.create();
    }

    public void addCard(final Card card) {
        participantCard.addCard(card);
    }

    public List<Card> getCard() {
        return List.copyOf(participantCard.getCards());
    }
}
