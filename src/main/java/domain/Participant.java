package domain;

import dto.ParticipantCardsDto;

import java.util.ArrayList;

public abstract class Participant {
    private final Name name;
    protected final Cards cards;

    public Participant(Name name) {
        this.name = name;
        cards = new Cards(new ArrayList<>());
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public boolean canReceiveCard() {
        return cards.canReceiveCard(getBustThreshold());
    };

    protected abstract int getBustThreshold();

    public String getName() {
        return name.getName();
    }

    public int getScore() {
        return cards.calculateOptimalScore();
    }

    public ParticipantCardsDto getParticipantCardsDto() {
        return new ParticipantCardsDto(name.getName(), cards.getCardsInfo(), getScore());
    }
}
