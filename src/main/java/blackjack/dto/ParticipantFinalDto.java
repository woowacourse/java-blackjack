package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;

import java.util.stream.Collectors;

public record ParticipantFinalDto(String name, String cards, int score) {

    private static final String JOIN_DELIMITER = ", ";

    public ParticipantFinalDto(Participant participant) {
        this(participant.getName(),
                participant.getCards().stream()
                        .map(Card::toDisplayName)
                        .collect(Collectors.joining(JOIN_DELIMITER)),
                participant.calculateScore().value());
    }
}
