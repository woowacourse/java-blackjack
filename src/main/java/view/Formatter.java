package view;

import domain.card.Card;
import domain.card.Suit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.dto.ParticipantCards;
import view.dto.ParticipantsProfit;
import view.dto.PlayerProfit;

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

    public static String participantsProfitResult(ParticipantsProfit result) {
        String dealerLine = dealerProfit(result);
        String playersLines = playersProfit(result);

        return String.join(System.lineSeparator(), dealerLine, playersLines);
    }

    private static String dealerProfit(ParticipantsProfit result) {
        return "딜러: " + result.dealerProfit();
    }

    private static String playersProfit(ParticipantsProfit result) {
        return result.playersProfitResult().stream()
                .map(Formatter::playerProfitResult)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private static String playerProfitResult(PlayerProfit result) {
        return result.name() + ": " + result.profit();
    }

}
