package blackjack.view;

import blackjack.common.Constants;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.cardholder.CardHolder;
import blackjack.domain.result.GameResultType;
import blackjack.domain.result.PlayerResult;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Formatter {

    private Formatter() {
    }

    public static String formatPlayerCardResult(Player player) {
        String message = formatPlayerCardStatus(player) + " - 결과: ";
        CardHolder cardHolder = player.getCardHolder();

        return message + formatCardResultValue(cardHolder);
    }

    public static String formatPlayerCardStatus(Player player) {
        return player.getName() + "카드: " + formatStartingCardStatus(player.getCardHolder());
    }

    public static String formatDealerStartCardStatus(Dealer dealer) {
        return "딜러카드: " + Formatter.formatCard(dealer.revealFirstCard());
    }

    public static String formatDealerCardResult(Dealer dealer) {
        CardHolder cardHolder = dealer.getCardHolder();

        return formatDealerCardStatus(dealer) + " - 결과: " + formatCardResultValue(cardHolder);
    }

    private static String formatDealerCardStatus(Dealer dealer) {
        return "딜러카드: " + formatStartingCardStatus(dealer.getCardHolder());
    }

    private static String formatCardResultValue(CardHolder cardHolder) {
        int value = cardHolder.getOptimisticValue();

        if (value == Constants.BUSTED_VALUE) {
            return "Busted!";
        }

        return java.lang.String.valueOf(value);
    }

    private static String formatStartingCardStatus(CardHolder cardHolder) {
        return cardHolder.getAllCards().stream()
                .map(Formatter::formatCard)
                .collect(Collectors.joining(", "));
    }

    public static String formatDealerGameResult(Map<GameResultType, Integer> dealerResult) {
        return Arrays.stream(GameResultType.values())
                .map(gameResultType -> formatResultCount(dealerResult, gameResultType))
                .collect(Collectors.joining(" "));
    }

    public static String formatPlayerGameResult(List<PlayerResult> playersResults) {
        return playersResults.stream()
                .map(result -> {
                    String nameOfPlayer = result.getPlayerName();
                    GameResultType gameResultType = result.getGameResultType();
                    return nameOfPlayer + ": " + formatGameResult(gameResultType);
                }).collect(Collectors.joining(System.lineSeparator()));
    }

    private static String formatResultCount(Map<GameResultType, Integer> results, GameResultType gameResultType) {
        Integer count = results.getOrDefault(gameResultType, 0);
        if (count == 0) {
            return "";
        }

        return count + formatGameResult(gameResultType);
    }

    private static String formatGameResult(GameResultType gameResultType) {
        if (gameResultType.equals(GameResultType.WIN)) {
            return "승";
        }

        if (gameResultType.equals(GameResultType.LOSE)) {
            return "패";
        }

        return "무";
    }

    public static String formatCard(Card card) {
        CardRank rank = card.getRank();
        CardSuit suit = card.getSuit();

        String formatCardRank = formatCardRank(rank);
        String formatCardSuit = formatCardSuit(suit);

        return formatCardRank + formatCardSuit;
    }

    private static String formatCardRank(CardRank cardRank) {
        if (cardRank == CardRank.ACE) {
            return "A";
        }

        if (cardRank == CardRank.KING) {
            return "K";
        }

        if (cardRank == CardRank.QUEEN) {
            return "Q";
        }

        if (cardRank == CardRank.JACK) {
            return "J";
        }

        List<Integer> values = cardRank.getValues();
        return String.valueOf(values.getFirst());
    }

    private static String formatCardSuit(CardSuit cardSuit) {
        if (cardSuit == CardSuit.SPADE) {
            return "스페이드";
        }

        if (cardSuit == CardSuit.CLUB) {
            return "클로버";
        }
        if (cardSuit == CardSuit.DIAMOND) {
            return "다이아몬드";
        }
        return "하트";
    }
}
