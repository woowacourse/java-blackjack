package blackjack.view;

import blackjack.common.Constants;
import blackjack.domain.Card;
import blackjack.domain.CardHolder;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import java.util.stream.Collectors;

public final class Parser {

    private Parser() {
    }

    public static String parsePlayerResult(Player player) {
        String message = parsePlayerCardStatus(player) + " - 결과: ";
        CardHolder cardHolder = player.getCardHolder();

        return message + parseResultValue(cardHolder);
    }

    public static String parsePlayerCardStatus(Player player) {
        return player.getName() + "카드: " + parseStartingCardStatus(player.getCardHolder());
    }

    public static String parseDealerStartCardStatus(Dealer dealer) {
        return "딜러카드: " + dealer.revealFirstCard().toCardName();
    }

    public static String parseDealerResult(Dealer dealer) {
        CardHolder cardHolder = dealer.getCardHolder();

        return parseDealerCardStatus(dealer) + " - 결과: " + parseResultValue(cardHolder);
    }

    private static String parseDealerCardStatus(Dealer dealer) {
        return "딜러카드: " + parseStartingCardStatus(dealer.getCardHolder());
    }

    private static String parseResultValue(CardHolder cardHolder) {
        int value = cardHolder.getOptimisticValue();

        if (value == Constants.BUSTED_VALUE) {
            return "Busted!";
        }

        return java.lang.String.valueOf(value);
    }

    private static String parseStartingCardStatus(CardHolder cardHolder) {
        return cardHolder.getAllCards().stream()
                .map(Card::toCardName)
                .collect(Collectors.joining(", "));
    }
}
