package controller;

import domain.Card;
import domain.Number;
import domain.Participant;
import java.util.List;
import java.util.stream.Collectors;

public class ParticipantDto {
    private final String name;
    private final List<String> cards;

    public ParticipantDto(Participant participant) {
        this.name = participant.name();
        this.cards = participant.hand()
                .stream()
                .map(card -> String.join("", renderCardNumber(card), card.suit()))
                .collect(Collectors.toUnmodifiableList());
    }

    private String renderCardNumber(Card card) {
        if (card.number() == Number.ACE) {
            return "A";
        }
        if (card.number() == Number.KING) {
            return "K";
        }
        if (card.number() == Number.QUEEN) {
            return "Q";
        }
        if (card.number() == Number.JACK) {
            return "J";
        }
        return Integer.toString(card.score());
    }

    public String name() {
        return name;
    }

    public List<String> cards() {
        return cards;
    }
}
