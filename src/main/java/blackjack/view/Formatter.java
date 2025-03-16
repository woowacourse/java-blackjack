package blackjack.view;

import static blackjack.common.Constants.LINE_SEPARATOR;

import blackjack.common.Constants;
import blackjack.domain.Card;
import blackjack.domain.CardRank;
import blackjack.domain.CardSuit;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import java.util.Map;
import java.util.stream.Collectors;

public final class Formatter {

    private Formatter() {
    }

    public static String formatCardResult(Player player) {
        String message = formatHand(player) + " - 결과: ";

        return message + formatCardResultValue(player);
    }

    public static String formatHand(Player player) {
        return player.getName() + "카드: " + formatStartingCardStatus(player);
    }

    private static String formatCardResultValue(Participant participant) {
        int value = participant.getBestCardValue();

        if (value == Constants.BUSTED_VALUE) {
            return "Busted!";
        }

        return java.lang.String.valueOf(value);
    }

    private static String formatStartingCardStatus(Participant participant) {
        return participant.getAllCards()
                .stream()
                .map(Formatter::formatCardName)
                .collect(Collectors.joining(", "));
    }

    private static String formatCardName(Card card) {
        CardRank cardRank = card.getCardRank();
        CardSuit cardSuit = card.getCardSuit();

        return CardRankTranslator.getDescription(cardRank) + CardSuitTranslator.getDescription(cardSuit);
    }

    public static String formatCardResult(Dealer dealer) {

        return formatHand(dealer) + " - 결과: " + formatCardResultValue(dealer);
    }

    private static String formatHand(Dealer dealer) {
        return "딜러카드: " + formatStartingCardStatus(dealer);
    }

    public static String formatUpCard(Dealer dealer) {
        return "딜러카드: " + formatCardName(dealer.revealFirstCard());
    }

    public static String formatPlayerRevenue(Map<Player, Integer> revenueMap) {
        return revenueMap.entrySet()
                .stream()
                .map(entry -> {
                    String name = entry.getKey()
                            .getName();
                    int revenue = entry.getValue();
                    return name + ": " + revenue;
                })
                .collect(Collectors.joining(LINE_SEPARATOR));
    }
}
