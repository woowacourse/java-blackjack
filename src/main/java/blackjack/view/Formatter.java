package blackjack.view;

import blackjack.common.Constants;
import blackjack.domain.Card;
import blackjack.domain.CardHolder;
import blackjack.domain.CardRank;
import blackjack.domain.CardSuit;
import blackjack.domain.Dealer;
import blackjack.domain.GameResultType;
import blackjack.domain.Player;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public final class Formatter {

    private Formatter() {
    }

    public static String parseCardResult(Player player) {
        String message = parseCardStatus(player) + " - 결과: ";
        CardHolder cardHolder = player.getCardHolder();

        return message + parseCardResultValue(cardHolder);
    }

    public static String parseCardResult(Dealer dealer) {
        CardHolder cardHolder = dealer.getCardHolder();

        return parseCardStatus(dealer) + " - 결과: " + parseCardResultValue(cardHolder);
    }

    public static String parseCardStatus(Player player) {
        return player.getName() + "카드: " + parseStartingCardStatus(player.getCardHolder());
    }

    public static String parseStartCardStatus(Dealer dealer) {
        return "딜러카드: " + parseCardName(dealer.revealFirstCard());
    }

    private static String parseCardStatus(Dealer dealer) {
        return "딜러카드: " + parseStartingCardStatus(dealer.getCardHolder());
    }

    private static String parseCardResultValue(CardHolder cardHolder) {
        int value = cardHolder.getOptimisticValue();

        if (value == Constants.BUSTED_VALUE) {
            return "Busted!";
        }

        return java.lang.String.valueOf(value);
    }

    private static String parseStartingCardStatus(CardHolder cardHolder) {
        return cardHolder.getAllCards().stream()
                .map(Formatter::parseCardName)
                .collect(Collectors.joining(", "));
    }

    public static String parseDealerGameResult(Map<GameResultType, Integer> dealerResult) {
        return Arrays.stream(GameResultType.values())
                .map(gameResultType -> parseResultCount(dealerResult, gameResultType))
                .collect(Collectors.joining(" "));
    }

    public static String parsePlayerGameResult(Map<Player, GameResultType> playersResult) {
        return playersResult.entrySet().stream().map(entry -> {
            Player player = entry.getKey();
            GameResultType resultType = entry.getValue();

            return player.getName() + ": " + parseGameResult(resultType);
        }).collect(Collectors.joining(System.lineSeparator()));
    }

    private static String parseResultCount(Map<GameResultType, Integer> results, GameResultType gameResultType) {
        Integer count = results.getOrDefault(gameResultType, 0);
        if (count == 0) {
            return "";
        }

        return count + parseGameResult(gameResultType);
    }

    private static String parseGameResult(GameResultType gameResultType) {
        if (gameResultType.equals(GameResultType.WIN)) {
            return "승";
        }

        if (gameResultType.equals(GameResultType.LOSE)) {
            return "패";
        }

        return "무";
    }

    private static String parseCardName(Card card) {
        CardRank cardRank = card.getCardRank();
        CardSuit cardSuit = card.getCardSuit();

        return CardRankTranslator.getDescription(cardRank) + CardSuitTranslator.getDescription(cardSuit);
    }
}
