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

    public static String parseCardResult(Player player) {
        String message = parseCardStatus(player) + " - 결과: ";

        return message + parseCardResultValue(player);
    }

    public static String parseCardResult(Dealer dealer) {

        return parseCardStatus(dealer) + " - 결과: " + parseCardResultValue(dealer);
    }

    public static String parseCardStatus(Player player) {
        return player.getName() + "카드: " + parseStartingCardStatus(player);
    }

    public static String parseStartCardStatus(Dealer dealer) {
        return "딜러카드: " + parseCardName(dealer.revealFirstCard());
    }

    private static String parseCardStatus(Dealer dealer) {
        return "딜러카드: " + parseStartingCardStatus(dealer);
    }

    private static String parseCardResultValue(Participant participant) {
        int value = participant.getOptimisticValue();

        if (value == Constants.BUSTED_VALUE) {
            return "Busted!";
        }

        return java.lang.String.valueOf(value);
    }

    private static String parseStartingCardStatus(Participant participant) {
        return participant.getAllCards().stream()
                .map(Formatter::parseCardName)
                .collect(Collectors.joining(", "));
    }

    public static String parsePlayerRevenue(Map<Player, Integer> revenueMap) {
        return revenueMap.entrySet().stream().map(
                entry -> {
                    String name = entry.getKey().getName();
                    int revenue = entry.getValue();

                    return name + ": " + revenue;
                }
        ).collect(Collectors.joining(LINE_SEPARATOR));
    }

    private static String parseCardName(Card card) {
        CardRank cardRank = card.getCardRank();
        CardSuit cardSuit = card.getCardSuit();

        return CardRankTranslator.getDescription(cardRank) + CardSuitTranslator.getDescription(cardSuit);
    }
}
