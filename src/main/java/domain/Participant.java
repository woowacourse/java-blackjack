package domain;

import dto.ParticipantCardsDto;

import java.util.ArrayList;

public abstract class Participant {
    private static final int BUST_THRESHOLD = 21;
    private final Name name;
    protected final ParticipantCards participantCards;

    public Participant(Name name) {
        this.name = name;
        participantCards = new ParticipantCards(new Cards(new ArrayList<>()));
    }

    public void receiveCard(Card card) {
        participantCards.addCard(card);
    }

    public abstract boolean isContinueGame();

    public String getName() {
        return name.getName();
    }

    public int getScore() {
        return participantCards.calculateScore();
    }

    public boolean isBust() {
        if (this.getScore() > BUST_THRESHOLD) {
            return true;
        }
        return false;
    }

    public ParticipantCardsDto getParticipantCardsDto() {
        return new ParticipantCardsDto(name.getName(), participantCards.getCardsInfo(), getScore());
    }
}
