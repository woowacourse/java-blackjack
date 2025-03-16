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
        String message = parseHand(player) + " - 결과: ";

        return message + parseCardResultValue(player);
    }

    public static String parseHand(Player player) {
        return player.getName() + "카드: " + parseStartingCardStatus(player);
    }

    private static String parseCardResultValue(Participant participant) {
        int value = participant.getBestCardValue();

        if (value == Constants.BUSTED_VALUE) {
            return "Busted!";
        }

        return java.lang.String.valueOf(value);
    }

    private static String parseStartingCardStatus(Participant participant) {
        return participant.getAllCards()
                .stream()
                .map(Formatter::parseCardName)
                .collect(Collectors.joining(", "));
    }

    private static String parseCardName(Card card) {
        CardRank cardRank = card.getCardRank();
        CardSuit cardSuit = card.getCardSuit();

        return CardRankTranslator.getDescription(cardRank) + CardSuitTranslator.getDescription(cardSuit);
    }

    public static String parseCardResult(Dealer dealer) {

        return parseHand(dealer) + " - 결과: " + parseCardResultValue(dealer);
    }

    private static String parseHand(Dealer dealer) {
        return "딜러카드: " + parseStartingCardStatus(dealer);
    }

    public static String parseUpCard(Dealer dealer) {
        return "딜러카드: " + parseCardName(dealer.revealFirstCard());
    }

    public static String parsePlayerRevenue(Map<Player, Integer> revenueMap) {
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
