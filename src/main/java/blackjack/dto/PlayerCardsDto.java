package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;

import java.util.stream.Collectors;

public record PlayerCardsDto(String name, String cards) {

    private static final String JOIN_DELIMITER = ", ";

    public PlayerCardsDto(Participant participant) {
        this(participant.getName(),
                participant.getCards().stream()
                        .map(Card::toDisplayName)
                        .collect(Collectors.joining(JOIN_DELIMITER)));
    }
}
