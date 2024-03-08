package view.dto.participant;

import domain.participant.Name;
import view.dto.card.CardsDto;

public class ParticipantDto {

    private final Name name;
    private final CardsDto cards;

    public ParticipantDto(final Name name, final CardsDto cards) {
        this.name = name;
        this.cards = cards;
    }

    public String name() {
        return name.value();
    }

    public int score() {
        return cards.getScore();
    }

    public CardsDto getCards() {
        return cards;
    }
}
