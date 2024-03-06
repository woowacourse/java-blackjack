package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record StartCardsDTO(CardDTO dealerCard, Map<String, List<CardDTO>> playersCard) {

    public static StartCardsDTO of(final Card dealerCard, final Map<PlayerName, Hands> playersCard) {
        return new StartCardsDTO(CardDTO.from(dealerCard), convertToDTO(playersCard));
    }

    private static Map<String, List<CardDTO>> convertToDTO(final Map<PlayerName, Hands> rawPlayersCard) {
        final Map<String, List<CardDTO>> playersCard = new LinkedHashMap<>();
        rawPlayersCard.forEach((key, value) -> playersCard.put(key.getName(), convertToCardDTO(value.getCards())));

        return playersCard;
    }

    private static List<CardDTO> convertToCardDTO(final List<Card> values) {
        return values.stream()
                .map(CardDTO::from)
                .toList();
    }
}
