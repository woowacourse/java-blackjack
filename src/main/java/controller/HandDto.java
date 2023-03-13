package controller;

import domain.Participant;

import java.util.List;
import java.util.stream.Collectors;

public class HandDto {
    private final String name;
    private final List<String> cards;

    public HandDto(final Participant participant) {
        this.name = participant.name();
        this.cards = participant.getHand()
                                .stream()
                                .map(card -> String.join("", String.valueOf(card.score()), card.suit()))
                                .collect(Collectors.toUnmodifiableList());
    }

    public String name() {
        return name;
    }

    public List<String> cards() {
        return cards;
    }
}
