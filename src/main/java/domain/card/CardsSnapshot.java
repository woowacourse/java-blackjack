package domain.card;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record CardsSnapshot(List<Card> cards) {
    private static final Map<Suit, String> SUIT_FORMATS = Map.of(
            Suit.CLOVER, "클로버",
            Suit.HEART, "하트",
            Suit.DIAMOND, "다이아몬드",
            Suit.SPADE, "스페이드"
    );

    public String getFormattedCards() {
        return cards.stream()
                .map(card -> card.rankSymbol() + formatSuit(card.suit()))
                .collect(Collectors.joining(", "));
    }

    private String formatSuit(Suit suit) {
        validateSuitFormatExist(suit);
        return SUIT_FORMATS.get(suit);
    }

    private static void validateSuitFormatExist(Suit suit) {
        if (!SUIT_FORMATS.containsKey(suit)) {
            throw new IllegalArgumentException();
        }
    }

    public int size() {
        return cards().size();
    }
}
