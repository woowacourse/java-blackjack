package view;

import domain.card.Card;
import domain.card.Suit;
import domain.dto.ParticipantCards;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Formatter {
    private static final Map<Suit, String> SUIT_FORMATS = Map.of(
            Suit.CLOVER, "클로버",
            Suit.HEART, "하트",
            Suit.DIAMOND, "다이아몬드",
            Suit.SPADE, "스페이드"
    );

    public static String participantCards(List<ParticipantCards> allParticipantCards) {
        return allParticipantCards.stream()
                .map(Formatter::participantCards)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public static String participantCards(ParticipantCards participantCards) {
        String formattedCards = participantCards.cards().stream()
                .map(Formatter::card)
                .collect(Collectors.joining(", "));

        return participantCards.name() + ": " + formattedCards;
    }

    public static String card(Card card) {
        return card.rankSymbol() + suit(card.suit());
    }

    public static String suit(Suit suit) {
        validateSuitFormatExist(suit);
        return SUIT_FORMATS.get(suit);
    }

    private static void validateSuitFormatExist(Suit suit) {
        if (!SUIT_FORMATS.containsKey(suit)) {
            throw new IllegalArgumentException();
        }
    }
}
