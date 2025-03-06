package blackjack.view;

import blackjack.common.Constants;
import blackjack.domain.Card;
import blackjack.domain.CardHolder;
import blackjack.domain.Dealer;
import blackjack.domain.DealerResult;
import blackjack.domain.GameResultType;
import blackjack.domain.Player;
import blackjack.domain.PlayersResult;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public final class Parser {


    private Parser() {
    }

    public static String parsePlayerCardResult(Player player) {
        String message = parsePlayerCardStatus(player) + " - 결과: ";
        CardHolder cardHolder = player.getCardHolder();

        return message + parseCardResultValue(cardHolder);
    }

    public static String parsePlayerCardStatus(Player player) {
        return player.getName() + "카드: " + parseStartingCardStatus(player.getCardHolder());
    }

    public static String parseDealerStartCardStatus(Dealer dealer) {
        return "딜러카드: " + dealer.revealFirstCard().toCardName();
    }

    public static String parseDealerCardResult(Dealer dealer) {
        CardHolder cardHolder = dealer.getCardHolder();

        return parseDealerCardStatus(dealer) + " - 결과: " + parseCardResultValue(cardHolder);
    }

    private static String parseDealerCardStatus(Dealer dealer) {
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
                .map(Card::toCardName)
                .collect(Collectors.joining(", "));
    }

    public static String parseDealerGameResult(DealerResult dealerResult) {
        Map<GameResultType, Integer> results = dealerResult.getDealerResult();

        return Arrays.stream(GameResultType.values())
                .map(gameResultType -> parseResultCount(results, gameResultType))
                .collect(Collectors.joining(" "));
    }

    public static String parsePlayerGameResult(PlayersResult playersResult) {
        Map<Player, GameResultType> playerGameResultTypes = playersResult.getAllResult();
        return playerGameResultTypes.entrySet().stream().map(entry -> {
            Player player = entry.getKey();
            GameResultType resultType = entry.getValue();
            return player.getName() + ": " + resultType.getDescription();
        }).collect(Collectors.joining(System.lineSeparator()));
    }


    private static String parseResultCount(Map<GameResultType, Integer> results, GameResultType gameResultType) {
        Integer count = results.getOrDefault(gameResultType, 0);
        if (count == 0) {
            return "";
        }

        return count + gameResultType.getDescription();
    }


}
