package view.dto;

import java.util.List;

import domain.Card;
import domain.CardNumber;
import domain.CardShape;
import domain.Cards;
import domain.Name;

public class ParticipantDto {

    private final Name name;
    private final Cards cards;

    protected ParticipantDto(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public String name() {
        return name.value();
    }

    public List<String> cards() {
        return cards.toList()
                .stream()
                .map(this::parseCard)
                .toList();
    }

    private String parseCard(final Card card) {
        CardNumber number = card.number();
        CardShape shape = card.shape();
        return number.value() + shape.value();
    }
}
