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

    public abstract boolean canReceiveCard();

    public String getName() {
        return name.getName();
    }

    public int getScore() {
        return cards.calculateScore();
    }



    public ParticipantCardsDto getParticipantCardsDto() {
        return new ParticipantCardsDto(name.getName(), cards.getCardsInfo(), getScore());
    }
}
