package controller;

import domain.card.Card;
import domain.card.Number;
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
                .map(card -> String.join("", renderCardNumber(card), card.suit()))
                .collect(Collectors.toUnmodifiableList());
        return new ParticipantDto(name, cards);
    }

    public static ParticipantDto ofInitial(Participant participant) {
        String name = participant.name();
        List<String> cards = participant.initialHand().stream()
                .map(card -> String.join("", renderCardNumber(card), card.suit()))
                .collect(Collectors.toUnmodifiableList());
        return new ParticipantDto(name, cards);
    }

    private static String renderCardNumber(Card card) {
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
