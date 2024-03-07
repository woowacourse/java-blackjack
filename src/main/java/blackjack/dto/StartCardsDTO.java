package blackjack.dto;

import blackjack.domain.Card;
import blackjack.domain.Hands;
import blackjack.domain.PlayerName;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record StartCardsDTO(Map<String, List<CardDTO>> playersCard) {

    public static StartCardsDTO of(final Map<PlayerName, Hands> playersCard) {
        return new StartCardsDTO(convertToDTO(playersCard));
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
