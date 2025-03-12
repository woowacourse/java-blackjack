package blackjack.view;

import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GameResultType;
import blackjack.domain.result.PlayerResult;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Formatter {

    private static final int BUSTED_STANDARD_VALUE = 21;

    private Formatter() {
    }

    public static String formatPlayerCardResult(PlayerResult playerResult) {
        String message = formatPlayerCardStatus(playerResult.getPlayer()) + " - 결과: ";

        return message + formatCardResultValue(playerResult);
    }

    public static String formatPlayerCardStatus(Player player) {
        return player.getName() + "카드: " + formatStartingCardStatus(player.getCardHolder());
    }

    public static String formatDealerStartCardStatus(Dealer dealer) {
        return "딜러카드: " + Formatter.formatCard(dealer.revealFirstCard());
    }

    // TODO: Dealer 의 결과를 저장하는 기능을 구현한 뒤에 수정하기
    public static String formatDealerCardResult(Dealer dealer, DealerResult dealerResult) {
        return formatDealerCardStatus(dealer) + " - 결과: " + formatCardResultValue(dealerResult);
    }

    private static String formatDealerCardStatus(Dealer dealer) {
        return "딜러카드: " + formatStartingCardStatus(dealer.getCardHolder());
    }

    private static String formatCardResultValue(PlayerResult playerResult) {
        int value = playerResult.getValue();

        if (value > BUSTED_STANDARD_VALUE) {
            return "Busted!";
        }

        return java.lang.String.valueOf(value);
    }

    private static String formatCardResultValue(DealerResult dealerResult) {
        int value = dealerResult.getValue();

        // TODO: 매직 넘버 없애기
        if (value > 21) {
            return "Busted!";
        }

        return java.lang.String.valueOf(value);
    }

    private static String formatStartingCardStatus(Hand cardHolder) {
        return cardHolder.getAllCards().stream()
                .map(Formatter::formatCard)
                .collect(Collectors.joining(", "));
    }

    public static String formatDealerGameResult(DealerResult dealerResult) {
        Map<GameResultType, Integer> countsOfResultTypes = dealerResult.getCountsOfResultTypes();

        return Arrays.stream(GameResultType.values())
                .map(gameResultType -> formatResultCount(countsOfResultTypes, gameResultType))
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
