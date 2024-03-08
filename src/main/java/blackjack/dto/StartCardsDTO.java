package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record StartCardsDTO(Map<String, List<CardDTO>> participantsCard) {
    public static StartCardsDTO of(final Map<ParticipantName, Hands> participantsCard) {
        return new StartCardsDTO(convertToDTO(participantsCard));
    }

    private static Map<String, List<CardDTO>> convertToDTO(final Map<ParticipantName, Hands> rawParticipantsCard) {
        final Map<String, List<CardDTO>> participantsCard = new LinkedHashMap<>();
        rawParticipantsCard.forEach(
                (key, value) -> participantsCard.put(key.getName(), convertToCardDTO(value.getCards())));

        return participantsCard;
    }

    private static List<CardDTO> convertToCardDTO(final List<Card> values) {
        return values.stream()
                .map(CardDTO::from)
                .toList();
    }
}
