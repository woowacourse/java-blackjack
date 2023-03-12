package controller;

import domain.participant.Participant;
import java.util.List;
import java.util.stream.Collectors;

public class ParticipantDto {
    private final String name;
    private final List<String> cards;

    private ParticipantDto(String name, List<String> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static ParticipantDto of(Participant participant) {
        String name = participant.name();
        List<String> cards = participant.hand().stream()
                .map(card -> String.join("", card.number().value(), card.suit()))
                .collect(Collectors.toUnmodifiableList());
        return new ParticipantDto(name, cards);
    }

    public static ParticipantDto ofInitial(Participant participant) {
        String name = participant.name();
        List<String> cards = participant.initialHand().stream()
                .map(card -> String.join("", card.number().value(), card.suit()))
                .collect(Collectors.toUnmodifiableList());
        return new ParticipantDto(name, cards);
    }

    public String name() {
        return name;
    }

    public List<String> cards() {
        return cards;
    }
}
